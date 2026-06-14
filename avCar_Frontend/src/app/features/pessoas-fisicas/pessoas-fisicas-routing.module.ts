import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PessoaFisicaListComponent } from './pessoa-fisica-list/pessoa-fisica-list.component';

const routes: Routes = [{ path: '', component: PessoaFisicaListComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PessoasFisicasRoutingModule {}