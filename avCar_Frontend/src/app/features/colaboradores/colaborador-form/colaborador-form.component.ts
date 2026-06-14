import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Colaborador } from '../../../core/models/colaborador.model';
import { FuncaoColaborador } from '../../../core/models/funcao-colaborador.model';
import { ColaboradorService } from '../../../core/services/colaborador.service';
import { FuncaoColaboradorService } from '../../../core/services/funcao-colaborador.service';

@Component({
  selector: 'app-colaborador-form',
  templateUrl: './colaborador-form.component.html',
})
export class ColaboradorFormComponent implements OnInit {
  form!: FormGroup;
  isEdit = false;
  loading = false;
  funcoes: FuncaoColaborador[] = [];

  constructor(
    private fb: FormBuilder,
    private service: ColaboradorService,
    private funcaoService: FuncaoColaboradorService,
    private snackBar: MatSnackBar,
    private dialogRef: MatDialogRef<ColaboradorFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Colaborador | null,
  ) {}

  ngOnInit(): void {
    this.isEdit = !!this.data;
    this.form = this.fb.group({
      nome: [this.data?.nome ?? '', Validators.required],
      cpf: [this.data?.cpf ?? '', [Validators.required, Validators.pattern(/^\d{3}\.\d{3}\.\d{3}-\d{2}$/)]],
      dataAdmissao: [this.isoToDDMMYYYY(this.data?.dataAdmissao), [Validators.required, Validators.pattern(/^\d{2}\/\d{2}\/\d{4}$/)]],
      salario: [this.data?.salario ?? null, [Validators.min(0)]],
      telefone: [this.data?.telefone ?? ''],
      email: [this.data?.email ?? '', [Validators.email]],
      endereco: [this.data?.endereco ?? ''],
      idFuncoes: [this.data?.idFuncoes ?? []],
    });
    this.funcaoService.getAll(0, 100).subscribe(page => {
      this.funcoes = page.content;
    });
  }

  save(): void {
    if (this.form.invalid) return;
    this.loading = true;
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    const raw: any = this.form.value;
    const payload: Colaborador = {
      ...raw,
      dataAdmissao: this.ddmmyyyyToIso(raw.dataAdmissao),
    };

    const obs = this.isEdit
      ? this.service.update(this.data!.id!, payload)
      : this.service.create(payload);

    obs.subscribe({
      next: () => {
        this.snackBar.open(
          `Colaborador ${this.isEdit ? 'atualizado' : 'cadastrado'} com sucesso`,
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