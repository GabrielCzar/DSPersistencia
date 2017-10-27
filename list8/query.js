
var library = 
[
  {
    'isbn': 441,
    'titulo': 'Programação Orientada a Objetos',
    'ano_publicacao': 2012,
    'qtd_estoque': 1,
    'valor': 70.00,
    'editora': 'Ática'
      },      
      {
        'isbn': 732,
        'titulo': 'Lógica de Programação',
        'ano_publicacao': 2009,
        'qtd_estoque': 1,
        'valor': 60.00,
        'editora': 'FTD'
      }, 
      {
        'isbn': 863,
        'titulo': 'Engenharia de Software',
        'ano_publicacao': 2010,
        'qtd_estoque': 2,
        'valor': 40.00,
        'editora' : 'FTD'
      },
      {
        'isbn': 425,
        'titulo': 'Sistemas Operacionais',
        'ano_publicacao': 2010,
        'qtd_estoque': 3,
        'valor': 80.00 ,
        'editora': 'Melhoramentos',
      },  
      {
        'isbn': 376,
        'titulo': 'Redes de computadores',
        'ano_publicacao': 2008,
        'qtd_estoque': 1,
        'valor': 100.00,
        'editora': 'Melhoramentos'
      },
      {
        'isbn': 213,
        'titulo': 'Banco de dados',
        'ano_publicacao': 2011,
        'qtd_estoque': 2,
        'valor': 50.00,
        'editora': 'Novatec'
      },
      {
        'isbn': 659,
        'titulo': 'Java para Nerds',
        'ano_publicacao': 2010,
        'qtd_estoque': 3,
        'valor': 90.00,
        'editora': 'Novatec'
      },    
      { 
        'editora': 'Bookman'
      }
];

db.library.insert(library)

// 1 Questao 

// Obter o nome da editora, 
// a quantidade total de livros por editora 
// e o valor total (qtd_estoque * valor) dos livros para cada editora. 
// Somente considerar os livros publicados a partir de 2010.

db.library.aggregate([ 
  {
    $match: { 
      "ano_publicacao": { $gte: 2010 }
    }
  },
  {
    $group : {
      _id: "$editora",
      totalPrice: { $sum : { $multiply : ["$qtd_estoque", "$valor"] } }
    }
  }
]);

// Obter a quantidade total de livros disponíveis em estoque com valor unitário abaixo de R$ 100,00

db.library.aggregate([ 
  {
    $match: { 
      "valor": { $lt: 100 }
    }
  },
  {
    $group : {
      _id: null,
      totalQTD: { $sum : "$qtd_estoque" }
    }
  },
  {
    $project : {
      _id : false
    }
  }
]);

// 2 Questao

var editoras = [
  { "nome" : "Ática" , id : 1},
  { "nome" : "FTD" , id : 2},
  { "nome" : "Melhoramentos" , id : 3},
  { "nome" : "Novatec" , id : 4},
  { "nome" : "Bookman" , id : 5 }
];

db.Editoras.insert(editoras)

var livros = [
  { "isbn" : 441, "titulo" : "Programação Orientada a Objetos", "ano_publicacao" : 2012, "qtd_estoque" : 1, "valor" : 70 , id_editora : 1},
  { "isbn" : 732, "titulo" : "Lógica de Programação", "ano_publicacao" : 2009, "qtd_estoque" : 1, "valor" : 60 , id_editora : 2},
  { "isbn" : 863, "titulo" : "Engenharia de Software", "ano_publicacao" : 2010, "qtd_estoque" : 2, "valor" : 40 , id_editora : 2},
  { "isbn" : 425, "titulo" : "Sistemas Operacionais", "ano_publicacao" : 2010, "qtd_estoque" : 3, "valor" : 80 , id_editora : 3},
  { "isbn" : 376, "titulo" : "Redes de computadores", "ano_publicacao" : 2008, "qtd_estoque" : 1, "valor" : 100 , id_editora : 3},
  { "isbn" : 213, "titulo" : "Banco de dados", "ano_publicacao" : 2011, "qtd_estoque" : 2, "valor": 50 , id_editora : 4},
  { "isbn" : 659, "titulo" : "Java para Nerds", "ano_publicacao" : 2010, "qtd_estoque" : 3, "valor" : 90 , id_editora : 4}
];

db.Livros.insert(livros)

// Obter os títulos dos livros 
// e os nomes das suas respectivas editoras. 
// Os resultados devem ser exibidos em ordem crescente pelo título do livro. 
// Os livros que não possuem editora também devem aparecer na listagem.

db.Livros.aggregate([
  {$lookup: {from : 'Editoras', localField: 'id_editora' , foreignField: 'id', as: 'editora'}},
  {$project : {_id : false, titulo: true, editora: '$editora.nome' } },
  {$sort: { 'titulo' : 1 }}
]);

// Obter o nome da editora, 
// a quantidade total de livros por editora 
// e o valor total (qtd_estoque * valor) dos livros para cada editora. 
// Somente considerar os livros publicados a partir de 2010.

db.Livros.aggregate([
  {$match: {ano_publicacao : { $gte : 2010 }}},
  {$lookup: {from: 'Editoras', localField: 'id_editora', foreignField: 'id', as: 'editora'}},
  {$group: {_id: '$editora.nome', totalLivros: {$sum : "$qtd_estoque" }, totalValor: { $sum: { $multiply: ["$qtd_estoque", "$valor"] } } }},
  {$project: {_id: false, editora : '$_id', totalLivros: true, totalValor : true} }
]);
