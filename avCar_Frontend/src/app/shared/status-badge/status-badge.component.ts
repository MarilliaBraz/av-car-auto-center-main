import { Component, Input } from '@angular/core';

const STATUS_MAP: Record<string, { css: string; label: string }> = {
  'ORCAMENTO':  { css: 'status-orcamento',  label: 'Orçamento'  },
  'EXECUCAO':   { css: 'status-execucao',   label: 'Execução'   },
  'PAGAMENTO':  { css: 'status-pagamento',  label: 'Pagamento'  },
  'FINALIZADO': { css: 'status-finalizado', label: 'Finalizado' },
};

@Component({
  selector: 'app-status-badge',
  template: `
    <span class="status-badge" [ngClass]="cssClass">
      <span class="dot"></span>{{ label }}
    </span>
  `,
  styleUrls: ['./status-badge.component.scss'],
})
export class StatusBadgeComponent {
  @Input() status = '';

  get cssClass(): string { return STATUS_MAP[this.status]?.css ?? ''; }
  get label(): string    { return STATUS_MAP[this.status]?.label ?? this.status; }
}