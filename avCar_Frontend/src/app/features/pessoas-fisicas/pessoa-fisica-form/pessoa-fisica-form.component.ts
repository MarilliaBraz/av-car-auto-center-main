import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PessoaFisica } from '../../../core/models/pessoa-fisica.model';
import { PessoaFisicaService } from '../../../core/services/pessoa-fisica.service';

@Component({
  selector: 'app-pessoa-fisica-form',
  templateUrl: './pessoa-fisica-form.component.html',
})
export class PessoaFisicaFormComponent implements OnInit {
  form!: FormGroup;
  isEdit = false;
  loading = false;

  constructor(
    private fb: FormBuilder,
    private service: PessoaFisicaService,
    private snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<PessoaFisicaFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: PessoaFisica | null,
  ) {}

  ngOnInit(): void {
    this.isEdit = !!this.data;
    this.form = this.fb.group({
      nome: [this.data?.nome ?? '', [Validators.required, Validators.minLength(3)]],
      cpf: [this.data?.cpf ?? '', [Validators.required, Validators.pattern(/^\d{3}\.\d{3}\.\d{3}-\d{2}$/)]],
      dataNascimento: [this.isoToDDMMYYYY(this.data?.dataNascimento), [Validators.pattern(/^\d{2}\/\d{2}\/\d{4}$/)]],
      dataCadastro: [this.isoToDDMMYYYY(this.data?.dataCadastro), [Validators.required, Validators.pattern(/^\d{2}\/\d{2}\/\d{4}$/)]],
      telefone: [this.data?.telefone ?? '', Validators.required],
      email: [this.data?.email ?? '', [Validators.required, Validators.email]],
      endereco: [this.data?.endereco ?? '', Validators.required],
    });
  }

  save(): void {
    if (this.form.invalid) return;
    this.loading = true;
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    const raw: any = this.form.value;
    const payload: PessoaFisica = {
      ...raw,
      dataNascimento: this.ddmmyyyyToIso(raw.dataNascimento),
      dataCadastro: this.ddmmyyyyToIso(raw.dataCadastro),
    };

    const obs = this.isEdit
      ? this.service.update(this.data!.id!, payload)
      : this.service.create(payload);

    obs.subscribe({
      next: () => {
        this.snackBar.open(
          `Pessoa física ${this.isEdit ? 'atualizada' : 'cadastrada'} com sucesso`,
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
    const parts = s.split('-');
    if (parts.length !== 3) return '';
    return `${parts[2]}/${parts[1]}/${parts[0]}`;
  }

  private ddmmyyyyToIso(s: string | null | undefined): string {
    if (!s || s.length < 10) return '';
    const parts = s.split('/');
    if (parts.length !== 3) return '';
    return `${parts[2]}-${parts[1]}-${parts[0]}`;
  }

}