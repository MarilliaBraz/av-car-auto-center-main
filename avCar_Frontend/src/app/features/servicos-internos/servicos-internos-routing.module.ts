import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ServicoInternoListComponent } from './servico-interno-list/servico-interno-list.component';

const routes: Routes = [{ path: '', component: ServicoInternoListComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ServicosInternosRoutingModule {}