import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PessoaJuridicaListComponent } from './pessoa-juridica-list/pessoa-juridica-list.component';

const routes: Routes = [{ path: '', component: PessoaJuridicaListComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PessoasJuridicasRoutingModule {}