import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { FornecedoresRoutingModule } from './fornecedores-routing.module';
import { FornecedorListComponent } from './fornecedor-list/fornecedor-list.component';
import { FornecedorFormComponent } from './fornecedor-form/fornecedor-form.component';

@NgModule({
  declarations: [FornecedorListComponent, FornecedorFormComponent],
  imports: [SharedModule, FornecedoresRoutingModule],
})
export class FornecedoresModule {}