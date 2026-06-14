import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { ServicosInternosRoutingModule } from './servicos-internos-routing.module';
import { ServicoInternoListComponent } from './servico-interno-list/servico-interno-list.component';
import { ServicoInternoFormComponent } from './servico-interno-form/servico-interno-form.component';

@NgModule({
  declarations: [ServicoInternoListComponent, ServicoInternoFormComponent],
  imports: [SharedModule, ServicosInternosRoutingModule],
})
export class ServicosInternosModule {}