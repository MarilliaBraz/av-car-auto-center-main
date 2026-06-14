import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MarcaListComponent } from './marca-list/marca-list.component';

const routes: Routes = [{ path: '', component: MarcaListComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MarcasRoutingModule {}