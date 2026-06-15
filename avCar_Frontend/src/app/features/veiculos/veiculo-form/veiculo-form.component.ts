import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { forkJoin } from 'rxjs';
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

@Component({
  selector: 'app-veiculo-form',
  templateUrl: './veiculo-form.component.html',
})
export class VeiculoFormComponent implements OnInit {
  form!: FormGroup;
  isEdit = false;
  loading = false;
  modelos: Modelo[] = [];
  clientes: ClienteOpcaoForm[] = [];
  loadingClientes = false;

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
          Validators.pattern(/^[A-Z]{3}-\d{4}$|^[A-Z]{3}\d[A-Z]\d{2}$/),
        ],
      ],
      anoFabricacao: [this.data?.anoFabricacao ?? null, [Validators.required, Validators.min(1900)]],
      cor: [this.data?.cor ?? '', Validators.required],
      idModelo: [this.data?.idModelo ?? null, Validators.required],
      idCliente: [{ value: this.data?.idCliente ?? null, disabled: this.isEdit }],
    });

    this.modeloService.getAll(0, 200).subscribe(page => {
      this.modelos = page.content;
    });

    if (!this.isEdit) {
      this.carregarClientes();
    }
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

  toUpperCase(field: string, event: Event): void {
    const input = event.target as HTMLInputElement;
    const upper = input.value.toUpperCase();
    this.form.get(field)?.setValue(upper, { emitEvent: false });
    input.value = upper;
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