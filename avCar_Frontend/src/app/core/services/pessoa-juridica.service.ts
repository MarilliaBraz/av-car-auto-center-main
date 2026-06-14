import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CrudService } from './crud.service';
import { PessoaJuridica } from '../models/pessoa-juridica.model';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class PessoaJuridicaService extends CrudService<PessoaJuridica> {
  protected apiUrl = `${environment.apiUrl}/clientes/pessoas-juridicas`;

  constructor(http: HttpClient) {
    super(http);
  }
}