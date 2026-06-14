import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FuncaoColaborador } from '../../../core/models/funcao-colaborador.model';
import { FuncaoColaboradorService } from '../../../core/services/funcao-colaborador.service';

@Component({
  selector: 'app-funcao-form',
  templateUrl: './funcao-form.component.html',
})
export class FuncaoFormComponent implements OnInit {
  form!: FormGroup;
  isEdit = false;
  loading = false;

  constructor(
    private fb: FormBuilder,
    private service: FuncaoColaboradorService,
    private snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<FuncaoFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: FuncaoColaborador | null,
  ) {}

  ngOnInit(): void {
    this.isEdit = !!this.data;
    this.form = this.fb.group({
      nomeFuncao: [this.data?.nomeFuncao ?? '', Validators.required],
      descricao: [this.data?.descricao ?? '', Validators.required],
    });
  }

  save(): void {
    if (this.form.invalid) return;
    this.loading = true;
    const payload: FuncaoColaborador = this.form.value;

    const obs = this.isEdit
      ? this.service.update(this.data!.id!, payload)
      : this.service.create(payload);

    obs.subscribe({
      next: () => {
        this.snackBar.open(
          `Função ${this.isEdit ? 'atualizada' : 'criada'} com sucesso`,
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