import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { OrdensServicoRoutingModule } from './ordens-servico-routing.module';
import { OrdemServicoListComponent } from './ordem-servico-list/ordem-servico-list.component';
import { OrdemServicoFormComponent } from './ordem-servico-form/ordem-servico-form.component';
import { ServicosOsDialogComponent } from './servicos-os-dialog/servicos-os-dialog.component';

@NgModule({
  declarations: [OrdemServicoListComponent, OrdemServicoFormComponent, ServicosOsDialogComponent],
  imports: [SharedModule, OrdensServicoRoutingModule],
})
export class OrdensServicoModule {}