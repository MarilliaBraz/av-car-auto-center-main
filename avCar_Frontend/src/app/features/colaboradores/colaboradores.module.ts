import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { ColaboradoresRoutingModule } from './colaboradores-routing.module';
import { ColaboradorListComponent } from './colaborador-list/colaborador-list.component';
import { ColaboradorFormComponent } from './colaborador-form/colaborador-form.component';

@NgModule({
  declarations: [ColaboradorListComponent, ColaboradorFormComponent],
  imports: [SharedModule, ColaboradoresRoutingModule],
})
export class ColaboradoresModule {}