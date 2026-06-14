import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ModeloListComponent } from './modelo-list/modelo-list.component';

const routes: Routes = [{ path: '', component: ModeloListComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ModelosRoutingModule {}