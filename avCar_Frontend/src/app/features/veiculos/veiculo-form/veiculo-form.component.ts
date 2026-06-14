import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Modelo } from '../../../core/models/modelo.model';
import { Veiculo } from '../../../core/models/veiculo.model';
import { ModeloService } from '../../../core/services/modelo.service';
import { VeiculoService } from '../../../core/services/veiculo.service';

@Component({
  selector: 'app-veiculo-form',
  templateUrl: './veiculo-form.component.html',
})
export class VeiculoFormComponent implements OnInit {
  form!: FormGroup;
  isEdit = false;
  loading = false;
  modelos: Modelo[] = [];

  constructor(
    private fb: FormBuilder,
    private service: VeiculoService,
    private modeloService: ModeloService,
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
    });
    this.modeloService.getAll(0, 200).subscribe(page => {
      this.modelos = page.content;
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
    const payload: Veiculo = this.form.value;

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