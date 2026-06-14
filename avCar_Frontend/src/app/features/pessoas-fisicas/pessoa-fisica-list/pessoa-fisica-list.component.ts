import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { PessoaFisica } from '../../../core/models/pessoa-fisica.model';
import { PessoaFisicaService } from '../../../core/services/pessoa-fisica.service';
import { PessoaFisicaFormComponent } from '../pessoa-fisica-form/pessoa-fisica-form.component';
import { ConfirmDialogComponent, ConfirmDialogData } from '../../../shared/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-pessoa-fisica-list',
  templateUrl: './pessoa-fisica-list.component.html',
})
export class PessoaFisicaListComponent implements OnInit {
  displayedColumns = ['id', 'nome', 'cpf', 'telefone', 'email', 'actions'];
  dataSource = new MatTableDataSource<PessoaFisica>();
  totalElements = 0;
  pageSize = 10;
  pageIndex = 0;
  loading = false;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private service: PessoaFisicaService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
  ) {}

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.loading = true;
    this.service.getAll(this.pageIndex, this.pageSize).subscribe({
      next: page => {
        this.dataSource.data = page.content;
        this.totalElements = page.totalElements;
        this.loading = false;
      },
      error: () => {
        this.snackBar.open('Erro ao carregar pessoas físicas', 'Fechar', { duration: 3000 });
        this.loading = false;
      },
    });
  }

  onPage(event: PageEvent): void {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.load();
  }

  applyFilter(value: string): void {
    this.dataSource.filter = value.trim().toLowerCase();
  }

  openForm(item?: PessoaFisica): void {
    const dialogRef = this.dialog.open(PessoaFisicaFormComponent, {
      width: '600px',
      data: item ? { ...item } : null,
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) this.load();
    });
  }

  delete(item: PessoaFisica): void {
    this.dialog.open(ConfirmDialogComponent, {
      width: '420px',
      data: {
        title: 'Confirmar exclusão',
        message: `Deseja excluir "${item.nome}"? Esta ação não pode ser desfeita.`,
        confirmText: 'Excluir',
      } as ConfirmDialogData,
    }).afterClosed().subscribe(confirmed => {
      if (!confirmed) return;
      this.service.delete(item.id!).subscribe({
        next: () => {
          this.snackBar.open('Pessoa física excluída', 'Fechar', { duration: 3000 });
          this.load();
        },
        error: () => {
          this.snackBar.open('Erro ao excluir', 'Fechar', { duration: 3000 });
        },
      });
    });
  }
}