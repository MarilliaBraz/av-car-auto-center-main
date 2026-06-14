import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PessoaJuridica } from '../../../core/models/pessoa-juridica.model';
import { PessoaJuridicaService } from '../../../core/services/pessoa-juridica.service';

@Component({
  selector: 'app-pessoa-juridica-form',
  templateUrl: './pessoa-juridica-form.component.html',
})
export class PessoaJuridicaFormComponent implements OnInit {
  form!: FormGroup;
  isEdit = false;
  loading = false;

  constructor(
    private fb: FormBuilder,
    private service: PessoaJuridicaService,
    private snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<PessoaJuridicaFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: PessoaJuridica | null,
  ) {}

  ngOnInit(): void {
    this.isEdit = !!this.data;
    this.form = this.fb.group({
      razaoSocial: [this.data?.razaoSocial ?? '', Validators.required],
      nomeFantasia: [this.data?.nomeFantasia ?? ''],
      cnpj: [this.data?.cnpj ?? '', [Validators.required, Validators.pattern(/^\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2}$/)]],
      dataCadastro: [this.isoToDDMMYYYY(this.data?.dataCadastro), [Validators.required, Validators.pattern(/^\d{2}\/\d{2}\/\d{4}$/)]],
      telefone: [this.data?.telefone ?? ''],
      email: [this.data?.email ?? '', [Validators.email]],
      endereco: [this.data?.endereco ?? ''],
    });
  }

  save(): void {
    if (this.form.invalid) return;
    this.loading = true;
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    const raw: any = this.form.value;
    const payload: PessoaJuridica = {
      ...raw,
      dataCadastro: this.ddmmyyyyToIso(raw.dataCadastro),
    };

    const obs = this.isEdit
      ? this.service.update(this.data!.id!, payload)
      : this.service.create(payload);

    obs.subscribe({
      next: () => {
        this.snackBar.open(
          `Pessoa jurídica ${this.isEdit ? 'atualizada' : 'cadastrada'} com sucesso`,
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