import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { MarcasRoutingModule } from './marcas-routing.module';
import { MarcaListComponent } from './marca-list/marca-list.component';
import { MarcaFormComponent } from './marca-form/marca-form.component';

@NgModule({
  declarations: [MarcaListComponent, MarcaFormComponent],
  imports: [SharedModule, MarcasRoutingModule],
})
export class MarcasModule {}