import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { VeiculosRoutingModule } from './veiculos-routing.module';
import { VeiculoListComponent } from './veiculo-list/veiculo-list.component';
import { VeiculoFormComponent } from './veiculo-form/veiculo-form.component';
import { HistoricoProprietarioDialogComponent } from './historico-proprietario-dialog/historico-proprietario-dialog.component';
import { TransferirProprietarioDialogComponent } from './transferir-proprietario-dialog/transferir-proprietario-dialog.component';

@NgModule({
  declarations: [
    VeiculoListComponent,
    VeiculoFormComponent,
    HistoricoProprietarioDialogComponent,
    TransferirProprietarioDialogComponent,
  ],
  imports: [SharedModule, VeiculosRoutingModule],
})
export class VeiculosModule {}