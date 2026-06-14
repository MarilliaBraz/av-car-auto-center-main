import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Colaborador } from '../../../core/models/colaborador.model';
import { ColaboradorService } from '../../../core/services/colaborador.service';
import { ColaboradorFormComponent } from '../colaborador-form/colaborador-form.component';
import { ConfirmDialogComponent, ConfirmDialogData } from '../../../shared/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-colaborador-list',
  templateUrl: './colaborador-list.component.html',
})
export class ColaboradorListComponent implements OnInit {
  displayedColumns = ['id', 'nome', 'cpf', 'telefone', 'email', 'salario', 'actions'];
  dataSource = new MatTableDataSource<Colaborador>();
  totalElements = 0;
  pageSize = 10;
  pageIndex = 0;
  loading = false;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private service: ColaboradorService,
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
        this.snackBar.open('Erro ao carregar colaboradores', 'Fechar', { duration: 3000 });
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

  openForm(item?: Colaborador): void {
    const dialogRef = this.dialog.open(ColaboradorFormComponent, {
      width: '650px',
      data: item ? { ...item } : null,
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) this.load();
    });
  }

  delete(item: Colaborador): void {
    this.dialog.open(ConfirmDialogComponent, {
      width: '420px',
      data: {
        title: 'Confirmar exclusão',
        message: `Deseja excluir o colaborador "${item.nome}"? Esta ação não pode ser desfeita.`,
        confirmText: 'Excluir',
      } as ConfirmDialogData,
    }).afterClosed().subscribe(confirmed => {
      if (!confirmed) return;
      this.service.delete(item.id!).subscribe({
        next: () => {
          this.snackBar.open('Colaborador excluído', 'Fechar', { duration: 3000 });
          this.load();
        },
        error: () => {
          this.snackBar.open('Erro ao excluir', 'Fechar', { duration: 3000 });
        },
      });
    });
  }
}