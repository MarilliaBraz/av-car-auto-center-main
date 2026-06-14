import { NgModule } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { PessoasFisicasRoutingModule } from './pessoas-fisicas-routing.module';
import { PessoaFisicaListComponent } from './pessoa-fisica-list/pessoa-fisica-list.component';
import { PessoaFisicaFormComponent } from './pessoa-fisica-form/pessoa-fisica-form.component';

@NgModule({
  declarations: [PessoaFisicaListComponent, PessoaFisicaFormComponent],
  imports: [SharedModule, PessoasFisicasRoutingModule],
})
export class PessoasFisicasModule {}