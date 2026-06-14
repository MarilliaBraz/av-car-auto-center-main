import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { OrdensServicoRoutingModule } from './ordens-servico-routing.module';
import { OrdemServicoListComponent } from './ordem-servico-list/ordem-servico-list.component';
import { OrdemServicoFormComponent } from './ordem-servico-form/ordem-servico-form.component';

@NgModule({
  declarations: [OrdemServicoListComponent, OrdemServicoFormComponent],
  imports: [SharedModule, OrdensServicoRoutingModule],
})
export class OrdensServicoModule {}