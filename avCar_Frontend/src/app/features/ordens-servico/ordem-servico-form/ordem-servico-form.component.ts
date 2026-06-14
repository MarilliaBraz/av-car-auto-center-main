import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { OrdemServico, OrdemServicoStatus } from '../../../core/models/ordem-servico.model';
import { Veiculo } from '../../../core/models/veiculo.model';
import { OrdemServicoService } from '../../../core/services/ordem-servico.service';
import { VeiculoService } from '../../../core/services/veiculo.service';

@Component({
  selector: 'app-ordem-servico-form',
  templateUrl: './ordem-servico-form.component.html',
})
export class OrdemServicoFormComponent implements OnInit {
  form!: FormGroup;
  isEdit = false;
  loading = false;
  veiculos: Veiculo[] = [];

  statusOptions: OrdemServicoStatus[] = ['ORCAMENTO', 'EXECUCAO', 'PAGAMENTO', 'FINALIZADO'];
  statusLabels: Record<OrdemServicoStatus, string> = {
    ORCAMENTO:  'Orçamento',
    EXECUCAO:   'Execução',
    PAGAMENTO:  'Pagamento',
    FINALIZADO: 'Finalizado',
  };

  constructor(
    private fb: FormBuilder,
    private service: OrdemServicoService,
    private veiculoService: VeiculoService,
    private snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<OrdemServicoFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: OrdemServico | null,
  ) {}

  ngOnInit(): void {
    this.isEdit = !!this.data;
    this.form = this.fb.group({
      dataAbertura: [this.isoToDDMMYYYY(this.data?.dataAbertura), [Validators.required, Validators.pattern(/^\d{2}\/\d{2}\/\d{4}$/)]],
      dataFechamento: [this.isoToDDMMYYYY(this.data?.dataFechamento), Validators.pattern(/^\d{2}\/\d{2}\/\d{4}$/)],
      status: [this.data?.status ?? 'ORCAMENTO', Validators.required],
      valorTotal: [this.data?.valorTotal ?? null, [Validators.min(0)]],
      observacoes: [this.data?.observacoes ?? ''],
      idVeiculo: [this.data?.idVeiculo ?? null, Validators.required],
    });
    this.veiculoService.getAll(0, 200).subscribe(page => {
      this.veiculos = page.content;
    });
  }

  save(): void {
    if (this.form.invalid) return;
    this.loading = true;
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    const raw: any = this.form.value;
    const payload: OrdemServico = {
      ...raw,
      dataAbertura: this.ddmmyyyyToIso(raw.dataAbertura),
      dataFechamento: raw.dataFechamento ? this.ddmmyyyyToIso(raw.dataFechamento) : undefined,
    };

    const obs = this.isEdit
      ? this.service.update(this.data!.id!, payload)
      : this.service.create(payload);

    obs.subscribe({
      next: () => {
        this.snackBar.open(
          `Ordem de serviço ${this.isEdit ? 'atualizada' : 'criada'} com sucesso`,
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

  private isoToDDMMYYYY(s: string | null | undefined): string {
    if (!s) return '';
    const datePart = s.includes('T') ? s.split('T')[0] : s;
    const parts = datePart.split('-');
    if (parts.length !== 3) return '';
    return `${parts[2]}/${parts[1]}/${parts[0]}`;
  }

  private ddmmyyyyToIso(s: string | null | undefined): string {
    if (!s || s.length < 10) return '';
    const parts = s.split('/');
    if (parts.length !== 3) return '';
    return `${parts[2]}-${parts[1]}-${parts[0]}T00:00:00`;
  }
}