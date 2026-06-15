import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { forkJoin, of, Subject } from 'rxjs';
import { catchError, debounceTime, distinctUntilChanged, filter, switchMap, takeUntil, tap } from 'rxjs/operators';
import { ConsultaPlacaResponse } from '../../../core/models/consulta-placa.model';
import { Modelo } from '../../../core/models/modelo.model';
import { PessoaFisica } from '../../../core/models/pessoa-fisica.model';
import { PessoaJuridica } from '../../../core/models/pessoa-juridica.model';
import { Veiculo } from '../../../core/models/veiculo.model';
import { ModeloService } from '../../../core/services/modelo.service';
import { PessoaFisicaService } from '../../../core/services/pessoa-fisica.service';
import { PessoaJuridicaService } from '../../../core/services/pessoa-juridica.service';
import { VeiculoService } from '../../../core/services/veiculo.service';

export interface ClienteOpcaoForm {
  id: number;
  nome: string;
  tipo: string;
}

const PLACA_PATTERN = /^[A-Z]{3}-\d{4}$|^[A-Z]{3}\d[A-Z]\d{2}$/;

@Component({
  selector: 'app-veiculo-form',
  templateUrl: './veiculo-form.component.html',
})
export class VeiculoFormComponent implements OnInit, OnDestroy {
  form!: FormGroup;
  isEdit = false;
  loading = false;
  buscandoPlaca = false;
  modelos: Modelo[] = [];
  clientes: ClienteOpcaoForm[] = [];
  loadingClientes = false;

  private destroy$ = new Subject<void>();

  constructor(
    private fb: FormBuilder,
    private service: VeiculoService,
    private modeloService: ModeloService,
    private pessoaFisicaService: PessoaFisicaService,
    private pessoaJuridicaService: PessoaJuridicaService,
    private snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<VeiculoFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Veiculo | null,
  ) {}

  ngOnInit(): void {
    this.isEdit = !!this.data;
    this.form = this.fb.group({
      placa: [
        (this.data?.placa ?? '').toUpperCase(),
        [
          Validators.required,
          Validators.pattern(PLACA_PATTERN),
        ],
      ],
      anoFabricacao: [this.data?.anoFabricacao ?? null, [Validators.required, Validators.min(1900)]],
      cor: [this.data?.cor ?? '', Validators.required],
      idModelo: [this.data?.idModelo ?? null, Validators.required],
      idCliente: [{ value: this.data?.idCliente ?? null, disabled: this.isEdit }],
    });

    if (!this.isEdit) {
      this.configurarBuscaAutomatica();
    }

    this.modeloService.getAll(0, 200).subscribe(page => {
      this.modelos = page.content;
    });

    if (!this.isEdit) {
      this.carregarClientes();
    }
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  private configurarBuscaAutomatica(): void {
    this.form.get('placa')!.valueChanges.pipe(
      debounceTime(600),
      distinctUntilChanged(),
      filter(v => !!v && PLACA_PATTERN.test(v)),
      tap(() => { this.buscandoPlaca = true; }),
      switchMap(placa =>
        this.service.consultarPlacaExterna(placa).pipe(
          catchError(() => of(null))
        )
      ),
      takeUntil(this.destroy$)
    ).subscribe(dados => {
      this.buscandoPlaca = false;
      if (dados) {
        this.preencherDadosVeiculo(dados);
      }
    });
  }

  private preencherDadosVeiculo(dados: ConsultaPlacaResponse): void {
    const anoFabricacao = parseInt(dados.ano?.substring(0, 4) ?? '', 10) || null;
    const apiModelo = (dados.modelo ?? '').toUpperCase();
    const modeloMatch = this.modelos.find(m => {
      const nome = m.nomeModelo.toUpperCase();
      return nome === apiModelo || nome.includes(apiModelo) || apiModelo.includes(nome);
    });

    this.form.patchValue({
      anoFabricacao,
      cor: dados.cor ?? '',
      ...(modeloMatch ? { idModelo: modeloMatch.id } : {}),
    }, { emitEvent: false });

    this.snackBar.open(
      'Dados do veículo preenchidos automaticamente',
      'Fechar',
      { duration: 4000 }
    );
  }

  formatPlaca(event: Event): void {
    const input = event.target as HTMLInputElement;
    // Remove tudo que não for letra ou número, força maiúsculo, limita a 7 chars brutos
    const raw = input.value.replace(/[^A-Za-z0-9]/g, '').toUpperCase().slice(0, 7);

    let formatted = raw;
    // Placa antiga: a partir do 5º char, posições 3 e 4 são dígitos → ABC-XXXX
    if (raw.length >= 5 && /\d/.test(raw[3]) && /\d/.test(raw[4])) {
      formatted = raw.slice(0, 3) + '-' + raw.slice(3);
    }

    input.value = formatted;
    this.form.get('placa')?.setValue(formatted, { emitEvent: true });
  }

  carregarClientes(): void {
    this.loadingClientes = true;
    forkJoin({
      fisicas: this.pessoaFisicaService.getAll(0, 500),
      juridicas: this.pessoaJuridicaService.getAll(0, 500),
    }).subscribe({
      next: ({ fisicas, juridicas }) => {
        const fisicasOpcoes: ClienteOpcaoForm[] = fisicas.content.map((pf: PessoaFisica) => ({
          id: pf.id!,
          nome: pf.nome,
          tipo: 'Pessoa Física',
        }));
        const juridicasOpcoes: ClienteOpcaoForm[] = juridicas.content.map((pj: PessoaJuridica) => ({
          id: pj.id!,
          nome: pj.razaoSocial,
          tipo: 'Pessoa Jurídica',
        }));
        this.clientes = [...fisicasOpcoes, ...juridicasOpcoes].sort((a, b) => a.nome.localeCompare(b.nome));
        this.loadingClientes = false;
      },
      error: () => {
        this.snackBar.open('Erro ao carregar clientes', 'Fechar', { duration: 3000 });
        this.loadingClientes = false;
      },
    });
  }

  save(): void {
    if (this.form.invalid) return;
    this.loading = true;
    const payload: Veiculo = { ...this.form.getRawValue() };

    const obs = this.isEdit
      ? this.service.update(this.data!.id!, payload)
      : this.service.create(payload);

    obs.subscribe({
      next: () => {
        this.snackBar.open(
          `Veículo ${this.isEdit ? 'atualizado' : 'cadastrado'} com sucesso`,
          'Fechar',
          { duration: 3000 },
        );
        this.dialogRef.close(true);
      },
      error: () => {
        this.snackBar.open('Erro ao salvar', 'Fechar', { duration: 3000 });
        this.loading = false;
      },
    });
  }

  cancel(): void {
    this.dialogRef.close(false);
  }
}