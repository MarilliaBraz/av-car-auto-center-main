import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { ModelosRoutingModule } from './modelos-routing.module';
import { ModeloListComponent } from './modelo-list/modelo-list.component';
import { ModeloFormComponent } from './modelo-form/modelo-form.component';

@NgModule({
  declarations: [ModeloListComponent, ModeloFormComponent],
  imports: [SharedModule, ModelosRoutingModule],
})
export class ModelosModule {}