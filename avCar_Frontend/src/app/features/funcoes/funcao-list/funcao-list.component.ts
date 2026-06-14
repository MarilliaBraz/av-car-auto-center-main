import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { FuncaoColaborador } from '../../../core/models/funcao-colaborador.model';
import { FuncaoColaboradorService } from '../../../core/services/funcao-colaborador.service';
import { FuncaoFormComponent } from '../funcao-form/funcao-form.component';
import { ConfirmDialogComponent, ConfirmDialogData } from '../../../shared/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-funcao-list',
  templateUrl: './funcao-list.component.html',
})
export class FuncaoListComponent implements OnInit {
  displayedColumns = ['id', 'nomeFuncao', 'descricao', 'actions'];
  dataSource = new MatTableDataSource<FuncaoColaborador>();
  totalElements = 0;
  pageSize = 10;
  pageIndex = 0;
  loading = false;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private service: FuncaoColaboradorService,
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
        this.snackBar.open('Erro ao carregar funções', 'Fechar', { duration: 3000 });
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

  openForm(item?: FuncaoColaborador): void {
    const dialogRef = this.dialog.open(FuncaoFormComponent, {
      width: '500px',
      data: item ? { ...item } : null,
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) this.load();
    });
  }

  delete(item: FuncaoColaborador): void {
    this.dialog.open(ConfirmDialogComponent, {
      width: '420px',
      data: {
        title: 'Confirmar exclusão',
        message: `Deseja excluir a função "${item.nomeFuncao}"? Esta ação não pode ser desfeita.`,
        confirmText: 'Excluir',
      } as ConfirmDialogData,
    }).afterClosed().subscribe(confirmed => {
      if (!confirmed) return;
      this.service.delete(item.id!).subscribe({
        next: () => {
          this.snackBar.open('Função excluída', 'Fechar', { duration: 3000 });
          this.load();
        },
        error: () => {
          this.snackBar.open('Erro ao excluir', 'Fechar', { duration: 3000 });
        },
      });
    });
  }
}