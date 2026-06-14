import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OrdemServicoListComponent } from './ordem-servico-list/ordem-servico-list.component';

const routes: Routes = [{ path: '', component: OrdemServicoListComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class OrdensServicoRoutingModule {}