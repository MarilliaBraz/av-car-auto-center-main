import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { PessoasJuridicasRoutingModule } from './pessoas-juridicas-routing.module';
import { PessoaJuridicaListComponent } from './pessoa-juridica-list/pessoa-juridica-list.component';
import { PessoaJuridicaFormComponent } from './pessoa-juridica-form/pessoa-juridica-form.component';

@NgModule({
  declarations: [PessoaJuridicaListComponent, PessoaJuridicaFormComponent],
  imports: [SharedModule, PessoasJuridicasRoutingModule],
})
export class PessoasJuridicasModule {}