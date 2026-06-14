import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Modelo } from '../../../core/models/modelo.model';
import { ModeloService } from '../../../core/services/modelo.service';
import { ModeloFormComponent } from '../modelo-form/modelo-form.component';
import { ConfirmDialogComponent, ConfirmDialogData } from '../../../shared/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-modelo-list',
  templateUrl: './modelo-list.component.html',
})
export class ModeloListComponent implements OnInit {
  displayedColumns = ['id', 'nomeModelo', 'idMarca', 'actions'];
  dataSource = new MatTableDataSource<Modelo>();
  totalElements = 0;
  pageSize = 10;
  pageIndex = 0;
  loading = false;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private modeloService: ModeloService,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
  ) {}

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.loading = true;
    this.modeloService.getAll(this.pageIndex, this.pageSize).subscribe({
      next: page => {
        this.dataSource.data = page.content;
        this.totalElements = page.totalElements;
        this.loading = false;
      },
      error: () => {
        this.snackBar.open('Erro ao carregar modelos', 'Fechar', { duration: 3000 });
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

  openForm(modelo?: Modelo): void {
    const dialogRef = this.dialog.open(ModeloFormComponent, {
      width: '500px',
      data: modelo ? { ...modelo } : null,
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) this.load();
    });
  }

  delete(modelo: Modelo): void {
    this.dialog.open(ConfirmDialogComponent, {
      width: '420px',
      data: {
        title: 'Confirmar exclusão',
        message: `Deseja excluir o modelo "${modelo.nomeModelo}"? Esta ação não pode ser desfeita.`,
        confirmText: 'Excluir',
      } as ConfirmDialogData,
    }).afterClosed().subscribe(confirmed => {
      if (!confirmed) return;
      this.modeloService.delete(modelo.id!).subscribe({
        next: () => {
          this.snackBar.open('Modelo excluído com sucesso', 'Fechar', { duration: 3000 });
          this.load();
        },
        error: () => {
          this.snackBar.open('Erro ao excluir modelo', 'Fechar', { duration: 3000 });
        },
      });
    });
  }
}