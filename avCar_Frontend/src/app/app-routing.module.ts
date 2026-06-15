import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full',
  },
  {
    path: 'dashboard',
    loadChildren: () =>
      import('./features/dashboard/dashboard.module').then(m => m.DashboardModule),
  },
  {
    path: 'marcas',
    loadChildren: () =>
      import('./features/marcas/marcas.module').then(m => m.MarcasModule),
  },
  {
    path: 'modelos',
    loadChildren: () =>
      import('./features/modelos/modelos.module').then(m => m.ModelosModule),
  },
  {
    path: 'pessoas-fisicas',
    loadChildren: () =>
      import('./features/pessoas-fisicas/pessoas-fisicas.module').then(m => m.PessoasFisicasModule),
  },
  {
    path: 'pessoas-juridicas',
    loadChildren: () =>
      import('./features/pessoas-juridicas/pessoas-juridicas.module').then(m => m.PessoasJuridicasModule),
  },
  {
    path: 'fornecedores',
    loadChildren: () =>
      import('./features/fornecedores/fornecedores.module').then(m => m.FornecedoresModule),
  },
  {
    path: 'funcoes',
    loadChildren: () =>
      import('./features/funcoes/funcoes.module').then(m => m.FuncoesModule),
  },
  {
    path: 'colaboradores',
    loadChildren: () =>
      import('./features/colaboradores/colaboradores.module').then(m => m.ColaboradoresModule),
  },
  {
    path: 'veiculos',
    loadChildren: () =>
      import('./features/veiculos/veiculos.module').then(m => m.VeiculosModule),
  },
  {
    path: 'servicos-internos',
    loadChildren: () =>
      import('./features/servicos-internos/servicos-internos.module').then(m => m.ServicosInternosModule),
  },
  {
    path: 'ordens-servico',
    loadChildren: () =>
      import('./features/ordens-servico/ordens-servico.module').then(m => m.OrdensServicoModule),
  },
  {
    path: 'historico-proprietarios',
    loadChildren: () =>
      import('./features/historico-proprietarios/historico-proprietarios.module').then(m => m.HistoricoProprietariosModule),
  },
  {
    path: 'relatorios',
    loadChildren: () =>
      import('./features/relatorios/relatorios.module').then(m => m.RelatoriosModule),
  },
  { path: '**', redirectTo: 'dashboard' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}