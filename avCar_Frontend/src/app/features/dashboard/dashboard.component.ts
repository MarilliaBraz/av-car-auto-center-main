import { Component, OnInit } from '@angular/core';
import { forkJoin } from 'rxjs';
import { OrdemServicoService } from '../../core/services/ordem-servico.service';
import { PecaService } from '../../core/services/peca.service';

interface KpiCard {
  label: string;
  value: number;
  display: string;
  icon: string;
  route: string;
  accent: string;
}

interface StatusCount {
  label: string;
  count: number;
  css: string;
  barCss: string;
}

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit {
  greeting = '';
  loading = true;

  kpis: KpiCard[] = [
    { label: 'OS Abertos',         value: 0, display: '0',       icon: 'assignment',  route: '/ordens-servico', accent: '--amber'  },
    { label: 'Em Execução',        value: 0, display: '0',       icon: 'build',       route: '/ordens-servico', accent: '--blue'   },
    { label: 'Faturamento do Mês', value: 0, display: 'R$ 0,00', icon: 'payments',    route: '/ordens-servico', accent: '--green'  },
    { label: 'Peças em Estoque',   value: 0, display: '0',       icon: 'inventory_2', route: '/pecas',          accent: '--violet' },
  ];

  statusCounts: StatusCount[] = [];
  recentOrders: any[] = [];

  constructor(
    private ordemServicoService: OrdemServicoService,
    private pecaService: PecaService,
  ) {}

  ngOnInit(): void {
    this.greeting = this.buildGreeting();

    forkJoin([
      this.ordemServicoService.getAll(0, 1000),
      this.pecaService.getAll(0, 1000),
    ]).subscribe({
      next: ([os, pecas]) => {
        const items: any[] = os.content ?? [];
        const now = new Date();

        // KPI 0 — OS Abertos (em orçamento)
        const osAbertos = items.filter(o => o.status === 'ORCAMENTO').length;
        this.kpis[0].value = osAbertos;
        this.kpis[0].display = String(osAbertos);

        // KPI 1 — Em Execução
        const emExecucao = items.filter(o => o.status === 'EXECUCAO').length;
        this.kpis[1].value = emExecucao;
        this.kpis[1].display = String(emExecucao);

        // KPI 2 — Faturamento do Mês (PAGAMENTO + FINALIZADO no mês corrente)
        const faturamento = items
          .filter(o => o.status === 'PAGAMENTO' || o.status === 'FINALIZADO')
          .filter(o => {
            const d = new Date(o.dataAbertura);
            return d.getMonth() === now.getMonth() && d.getFullYear() === now.getFullYear();
          })
          .reduce((sum: number, o: any) => sum + (o.valorTotal ?? 0), 0);
        this.kpis[2].value = faturamento;
        this.kpis[2].display = faturamento.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });

        // KPI 3 — Total de Peças em Estoque
        const totalEstoque = (pecas.content ?? [])
          .reduce((sum: number, p: any) => sum + (p.estoqueAtual ?? 0), 0);
        this.kpis[3].value = totalEstoque;
        this.kpis[3].display = String(totalEstoque);

        // Status breakdown
        const statusDefs = [
          { label: 'Orçamento',  status: 'ORCAMENTO',  css: 'status-orcamento',  barCss: 'bar-orcamento'  },
          { label: 'Execução',   status: 'EXECUCAO',   css: 'status-execucao',   barCss: 'bar-execucao'   },
          { label: 'Pagamento',  status: 'PAGAMENTO',  css: 'status-pagamento',  barCss: 'bar-pagamento'  },
          { label: 'Finalizado', status: 'FINALIZADO', css: 'status-finalizado', barCss: 'bar-finalizado' },
        ];
        this.statusCounts = statusDefs.map(d => ({
          label: d.label,
          css: d.css,
          barCss: d.barCss,
          count: items.filter((o: any) => o.status === d.status).length,
        }));

        this.recentOrders = items.slice(0, 8);
        this.loading = false;
      },
      error: () => { this.loading = false; },
    });
  }

  get maxStatus(): number {
    return Math.max(...this.statusCounts.map(s => s.count), 1);
  }

  private buildGreeting(): string {
    const h = new Date().getHours();
    if (h < 12) return 'Bom dia';
    if (h < 18) return 'Boa tarde';
    return 'Boa noite';
  }
}