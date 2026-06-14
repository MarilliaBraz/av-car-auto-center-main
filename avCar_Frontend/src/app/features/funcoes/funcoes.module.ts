import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { FuncoesRoutingModule } from './funcoes-routing.module';
import { FuncaoListComponent } from './funcao-list/funcao-list.component';
import { FuncaoFormComponent } from './funcao-form/funcao-form.component';

@NgModule({
  declarations: [FuncaoListComponent, FuncaoFormComponent],
  imports: [SharedModule, FuncoesRoutingModule],
})
export class FuncoesModule {}