import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { VeiculosRoutingModule } from './veiculos-routing.module';
import { VeiculoListComponent } from './veiculo-list/veiculo-list.component';
import { VeiculoFormComponent } from './veiculo-form/veiculo-form.component';

@NgModule({
  declarations: [VeiculoListComponent, VeiculoFormComponent],
  imports: [SharedModule, VeiculosRoutingModule],
})
export class VeiculosModule {}