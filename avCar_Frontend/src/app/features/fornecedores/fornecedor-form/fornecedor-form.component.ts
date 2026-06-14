import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Fornecedor } from '../../../core/models/fornecedor.model';
import { FornecedorService } from '../../../core/services/fornecedor.service';

@Component({
  selector: 'app-fornecedor-form',
  templateUrl: './fornecedor-form.component.html',
})
export class FornecedorFormComponent implements OnInit {
  form!: FormGroup;
  isEdit = false;
  loading = false;

  constructor(
    private fb: FormBuilder,
    private service: FornecedorService,
    private snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<FornecedorFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Fornecedor | null,
  ) {}

  ngOnInit(): void {
    this.isEdit = !!this.data;
    this.form = this.fb.group({
      nomeFornecedor: [this.data?.nomeFornecedor ?? '', Validators.required],
      cnpj: [
        this.data?.cnpj ?? '',
        [Validators.pattern(/^\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2}$/)],
      ],
      telefone: [this.data?.telefone ?? '', Validators.required],
      email: [this.data?.email ?? '', [Validators.email]],
      endereco: [this.data?.endereco ?? ''],
    });
  }

  save(): void {
    if (this.form.invalid) return;
    this.loading = true;
    const payload: Fornecedor = this.form.value;

    const obs = this.isEdit
      ? this.service.update(this.data!.id!, payload)
      : this.service.create(payload);

    obs.subscribe({
      next: () => {
        this.snackBar.open(
          `Fornecedor ${this.isEdit ? 'atualizado' : 'cadastrado'} com sucesso`,
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