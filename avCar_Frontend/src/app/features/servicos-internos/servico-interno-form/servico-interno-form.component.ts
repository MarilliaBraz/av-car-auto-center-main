import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ServicoInterno } from '../../../core/models/servico-interno.model';
import { ServicoInternoService } from '../../../core/services/servico-interno.service';

@Component({
  selector: 'app-servico-interno-form',
  templateUrl: './servico-interno-form.component.html',
})
export class ServicoInternoFormComponent implements OnInit {
  form!: FormGroup;
  isEdit = false;
  loading = false;

  constructor(
    private fb: FormBuilder,
    private service: ServicoInternoService,
    private snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<ServicoInternoFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ServicoInterno | null,
  ) {}

  ngOnInit(): void {
    this.isEdit = !!this.data;
    this.form = this.fb.group({
      nomeServico: [this.data?.nomeServico ?? '', Validators.required],
      descricao: [this.data?.descricao ?? '', Validators.required],
      precoBase: [this.data?.precoBase ?? null, [Validators.required, Validators.min(0)]],
      tempoEstimadoMin: [this.data?.tempoEstimadoMin ?? null, [Validators.required, Validators.min(1)]],
    });
  }

  save(): void {
    if (this.form.invalid) return;
    this.loading = true;
    const payload: ServicoInterno = this.form.value;

    const obs = this.isEdit
      ? this.service.update(this.data!.id!, payload)
      : this.service.create(payload);

    obs.subscribe({
      next: () => {
        this.snackBar.open(
          `Serviço ${this.isEdit ? 'atualizado' : 'cadastrado'} com sucesso`,
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