import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CrudService } from './crud.service';
import { PessoaFisica } from '../models/pessoa-fisica.model';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class PessoaFisicaService extends CrudService<PessoaFisica> {
  protected apiUrl = `${environment.apiUrl}/clientes/pessoas-fisicas`;

  constructor(http: HttpClient) {
    super(http);
  }
}