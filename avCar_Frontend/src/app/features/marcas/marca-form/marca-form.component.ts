import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Marca } from '../../../core/models/marca.model';
import { MarcaService } from '../../../core/services/marca.service';

@Component({
  selector: 'app-marca-form',
  templateUrl: './marca-form.component.html',
})
export class MarcaFormComponent implements OnInit {
  form!: FormGroup;
  isEdit = false;
  loading = false;

  constructor(
    private fb: FormBuilder,
    private marcaService: MarcaService,
    private snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<MarcaFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Marca | null,
  ) {}

  ngOnInit(): void {
    this.isEdit = !!this.data;
    this.form = this.fb.group({
      nomeMarca: [this.data?.nomeMarca ?? '', [Validators.required, Validators.minLength(2)]],
    });
  }

  save(): void {
    if (this.form.invalid) return;
    this.loading = true;
    const payload: Marca = this.form.value;

    const obs = this.isEdit
      ? this.marcaService.update(this.data!.id!, payload)
      : this.marcaService.create(payload);

    obs.subscribe({
      next: () => {
        this.snackBar.open(
          `Marca ${this.isEdit ? 'atualizada' : 'criada'} com sucesso`,
          'Fechar',
          { duration: 3000 },
        );
        this.dialogRef.close(true);
      },
      error: () => {
        this.snackBar.open('Erro ao salvar marca', 'Fechar', { duration: 3000 });
        this.loading = false;
      },
    });
  }

  cancel(): void {
    this.dialogRef.close(false);
  }
}