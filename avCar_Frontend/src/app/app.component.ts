import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';

interface NavItem {
  label: string;
  route: string;
  icon: string;
}

interface NavGroup {
  label: string;
  items: NavItem[];
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  breadcrumb = 'Dashboard';

  navGroups: NavGroup[] = [
    {
      label: 'OPERAÇÃO',
      items: [
        { label: 'Dashboard', route: '/dashboard', icon: 'dashboard' },
        { label: 'Ordens de Serviço', route: '/ordens-servico', icon: 'assignment' },
        { label: 'Veículos', route: '/veiculos', icon: 'car_repair' },
      ],
    },
    {
      label: 'CADASTROS',
      items: [
        { label: 'Clientes PF', route: '/pessoas-fisicas', icon: 'person' },
        { label: 'Clientes PJ', route: '/pessoas-juridicas', icon: 'business' },
        { label: 'Colaboradores', route: '/colaboradores', icon: 'group' },
        { label: 'Fornecedores', route: '/fornecedores', icon: 'local_shipping' },
      ],
    },
    {
      label: 'SISTEMA',
      items: [
        { label: 'Serviços', route: '/servicos-internos', icon: 'build' },
        { label: 'Funções', route: '/funcoes', icon: 'work' },
        { label: 'Marcas', route: '/marcas', icon: 'branding_watermark' },
        { label: 'Modelos', route: '/modelos', icon: 'directions_car' },
      ],
    },
    {
      label: 'RELATÓRIOS',
      items: [
        { label: 'Histórico de Proprietários', route: '/historico-proprietarios', icon: 'history' },
        { label: 'Relatórios de Frota', route: '/relatorios', icon: 'bar_chart' },
      ],
    },
  ];

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.breadcrumb = this.resolveBreadcrumb(this.router.url);
    this.router.events
      .pipe(filter(e => e instanceof NavigationEnd))
      .subscribe(() => {
        this.breadcrumb = this.resolveBreadcrumb(this.router.url);
      });
  }

  private resolveBreadcrumb(url: string): string {
    const all = this.navGroups.flatMap(g => g.items);
    return all.find(i => url.startsWith(i.route))?.label ?? 'Dashboard';
  }
}