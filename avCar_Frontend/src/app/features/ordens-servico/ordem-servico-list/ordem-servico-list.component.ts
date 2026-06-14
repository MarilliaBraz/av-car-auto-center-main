import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { OrdemServico } from '../../../core/models/ordem-servico.model';
import { OrdemServicoService } from '../../../core/services/ordem-servico.service';
import { OrdemServicoFormComponent } from '../ordem-servico-form/ordem-servico-form.component';
import { ConfirmDialogComponent, ConfirmDialogData } from '../../../shared/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-ordem-servico-list',
  templateUrl: './ordem-servico-list.component.html',
})
export class OrdemServicoListComponent implements OnInit {
  displayedColumns = ['numero', 'dataAbertura', 'status', 'valorTotal', 'idVeiculo', 'actions'];
  dataSource = new MatTableDataSource<OrdemServico>();
  totalElements = 0;
  pageSize = 10;
  pageIndex = 0;
  loading = false;

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private service: OrdemServicoService,
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
        this.snackBar.open('Erro ao carregar ordens de serviço', 'Fechar', { duration: 3000 });
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

  openForm(item?: OrdemServico): void {
    const dialogRef = this.dialog.open(OrdemServicoFormComponent, {
      width: '600px',
      data: item ? { ...item } : null,
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) this.load();
    });
  }

  delete(item: OrdemServico): void {
    this.dialog.open(ConfirmDialogComponent, {
      width: '420px',
      data: {
        title: 'Confirmar exclusão',
        message: `Deseja excluir a ordem de serviço ${item.numero ?? '#' + item.id}? Esta ação não pode ser desfeita.`,
        confirmText: 'Excluir',
      } as ConfirmDialogData,
    }).afterClosed().subscribe(confirmed => {
      if (!confirmed) return;
      this.service.delete(item.id!).subscribe({
        next: () => {
          this.snackBar.open('Ordem de serviço excluída', 'Fechar', { duration: 3000 });
          this.load();
        },
        error: () => {
          this.snackBar.open('Erro ao excluir', 'Fechar', { duration: 3000 });
        },
      });
    });
  }

  getStatusClass(status: string): string {
    const map: Record<string, string> = {
      'Orçamento': 'status-orcamento',
      'Execução': 'status-execucao',
      'Pagamento': 'status-pagamento',
      'Finalizado': 'status-finalizado',
    };
    return map[status] ?? '';
  }
}