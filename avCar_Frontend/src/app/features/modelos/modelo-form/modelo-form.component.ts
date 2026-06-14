import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Marca } from '../../../core/models/marca.model';
import { Modelo } from '../../../core/models/modelo.model';
import { MarcaService } from '../../../core/services/marca.service';
import { ModeloService } from '../../../core/services/modelo.service';

@Component({
  selector: 'app-modelo-form',
  templateUrl: './modelo-form.component.html',
})
export class ModeloFormComponent implements OnInit {
  form!: FormGroup;
  isEdit = false;
  loading = false;
  marcas: Marca[] = [];

  constructor(
    private fb: FormBuilder,
    private modeloService: ModeloService,
    private marcaService: MarcaService,
    private snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<ModeloFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Modelo | null,
  ) {}

  ngOnInit(): void {
    this.isEdit = !!this.data;
    this.form = this.fb.group({
      nomeModelo: [this.data?.nomeModelo ?? '', [Validators.required, Validators.minLength(2)]],
      idMarca: [this.data?.idMarca ?? null, Validators.required],
    });
    this.marcaService.getAll(0, 100).subscribe(page => {
      this.marcas = page.content;
    });
  }

  save(): void {
    if (this.form.invalid) return;
    this.loading = true;
    const payload: Modelo = this.form.value;

    const obs = this.isEdit
      ? this.modeloService.update(this.data!.id!, payload)
      : this.modeloService.create(payload);

    obs.subscribe({
      next: () => {
        this.snackBar.open(
          `Modelo ${this.isEdit ? 'atualizado' : 'criado'} com sucesso`,
          'Fechar',
          { duration: 3000 },
        );
        this.dialogRef.close(true);
      },
      error: () => {
        this.snackBar.open('Erro ao salvar modelo', 'Fechar', { duration: 3000 });
        this.loading = false;
      },
    });
  }

  cancel(): void {
    this.dialogRef.close(false);
  }
}