import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FuncaoListComponent } from './funcao-list/funcao-list.component';

const routes: Routes = [{ path: '', component: FuncaoListComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class FuncoesRoutingModule {}