export interface Colaborador {
  id?: number;
  nome: string;
  cpf: string;
  dataAdmissao: string;
  salario: number;
  telefone: string;
  email: string;
  endereco: string;
  idFuncoes: number[];
  active: true;
}