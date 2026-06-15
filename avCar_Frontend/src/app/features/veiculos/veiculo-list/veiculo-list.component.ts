import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Veiculo } from '../../../core/models/veiculo.model';
import { VeiculoService } from '../../../core/services/veiculo.service';
import { VeiculoFormComponent } from '../veiculo-form/veiculo-form.component';
import { ConfirmDialogComponent, ConfirmDialogData } from '../../../shared/confirm-dialog/confirm-dialog.component';
import { HistoricoProprietarioDialogComponent } from '../historico-proprietario-dialog/historico-proprietario-dialog.component';
import { TransferirProprietarioDialogComponent } from '../transferir-proprietario-dialog/transferir-proprietario-dialog.component';

@Component({
  selector: 'app-veiculo-list',
  templateUrl: './veiculo-list.component.html',
})
export class VeiculoListComponent implements OnInit {
  displayedColumns = ['id', 'placa', 'anoFabricacao', 'cor', 'nomeModelo', 'nomeCliente', 'actions'];
  dataSource = new MatTableDataSource<Veiculo>();
  totalElements = 0;
  pageSize = 10;
  pageIndex = 0;
  loading = false;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private service: VeiculoService,
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
        this.snackBar.open('Erro ao carregar veículos', 'Fechar', { duration: 3000 });
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

  openForm(item?: Veiculo): void {
    const dialogRef = this.dialog.open(VeiculoFormComponent, {
      width: '550px',
      data: item ? { ...item } : null,
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) this.load();
    });
  }

  openHistorico(item: Veiculo): void {
    this.dialog.open(HistoricoProprietarioDialogComponent, {
      width: '700px',
      data: item,
    });
  }

  openTransferencia(item: Veiculo): void {
    this.dialog.open(TransferirProprietarioDialogComponent, {
      width: '500px',
      data: item,
    }).afterClosed().subscribe(result => {
      if (result) this.load();
    });
  }

  delete(item: Veiculo): void {
    this.dialog.open(ConfirmDialogComponent, {
      width: '420px',
      data: {
        title: 'Confirmar exclusão',
        message: `Deseja excluir o veículo "${item.placa}"? Esta ação não pode ser desfeita.`,
        confirmText: 'Excluir',
      } as ConfirmDialogData,
    }).afterClosed().subscribe(confirmed => {
      if (!confirmed) return;
      this.service.delete(item.id!).subscribe({
        next: () => {
          this.snackBar.open('Veículo excluído', 'Fechar', { duration: 3000 });
          this.load();
        },
        error: () => {
          this.snackBar.open('Erro ao excluir', 'Fechar', { duration: 3000 });
        },
      });
    });
  }
}