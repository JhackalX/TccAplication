/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import Object.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sqlite.*;
/**
 *
 * @author jacka
 */
public class CtrlDao {
    private String caminhoBanco;
    private Connection conexao;
    private EstacaoDAO estacaoDao;
    private DadosDAO dadosDao;
    private ColunaDAO colunaDAO;
    
    public CtrlDao(String caminhoBanco) {
        this.caminhoBanco = caminhoBanco;
        this.estacaoDao = new EstacaoDAO(this);
        this.dadosDao = new DadosDAO(this);
        this.colunaDAO = new ColunaDAO(this);
        this.conectarBanco();
        this.desconectarBanco();
    }
    
    
    public void conectarBanco() {
        try {
            this.conexao =  DriverManager.getConnection("jdbc:sqlite:"+this.caminhoBanco);
            
        }catch (SQLException e){
            System.out.println("Erro ao conecat na base de dados. Mensagem : "+e.getMessage());
        }
    }
    public void desconectarBanco (){
        if (this.conexao  != null ){
            try {
                this.conexao.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public static void inicializarBaseDados(String baseDados){
        
        try {
            System.out.println("teste");
            Connection conexaoBase =  DriverManager.getConnection("jdbc:sqlite:"+baseDados);
            if (conexaoBase != null) {
                DatabaseMetaData meta = conexaoBase.getMetaData();
                System.out.println("O driver utilizado é " + meta.getDriverName());
                System.out.println("uma nova bas foi criada..");
                populaBaseDados(conexaoBase);
                conexaoBase.close();
            } else {
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            
        }
    }
    private static void populaBaseDados(Connection conexaoBase) throws SQLException{
        
        Statement sttmBase =  conexaoBase.createStatement();
        sttmBase.setQueryTimeout(30);
        criarTabelas( sttmBase);
        popularTabelas( sttmBase);
        sttmBase.close();
    }
    
    private static void criarTabelas( Statement sttmBase) throws SQLException {
       //Criar tabla de metodologias
        sttmBase.executeUpdate("CREATE TABLE tb_sensor(\n" +
                                "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                                "nome TEXT(50),\n" +
                                "txt_arquivo_carga TEXT(255),\n" +
                                "unidade_medida TEXT(512));");
        sttmBase.executeUpdate("CREATE TABLE tb_metodologia (\n" +
                                "id TEXT(36) PRIMARY KEY UNIQUE,\n" +
                                "nome TEXT(50),\n" +
                                "dsc TEXT(255),\n" +
                                "dsc_ajuda TEXT(512));");
       //Criar tabela de estacoes
         sttmBase.executeUpdate("CREATE TABLE tb_estacao (\n" +
                                "nome TEXT(50),\n" +
                                "codigo TEXT(10),\n" +
                                "latitude REAL,\n" +
                                "longitude REAL,\n" +
                                "altitude REAL,\n" +
                                "data_fundacao TEXT(16),\n" +
                                "uf TEXT(2),\n" +
                                "regiao TEXT(2),\n" +
                                "id TEXT(36) PRIMARY KEY UNIQUE" +
                                ");");
       //Criar tabela de estudos
        sttmBase.executeUpdate("CREATE TABLE tb_estudo (\n" +
                                "id TEXT(36) PRIMARY KEY UNIQUE,\n" +
                                "id_estacao INTEGER,\n" +
                                "id_metodologia INTEGER,\n" +
                                "data_estudo TEXT(16),\n" +
                                "CONSTRAINT id_metodologia_fk FOREIGN KEY (id_metodologia) REFERENCES tb_metodologia(id),\n" +
                                "CONSTRAINT id_estacao_fk FOREIGN KEY (id_estacao) REFERENCES tb_estacao(id)\n" +
                                ");");
       //Criar tabela de dados processados
        sttmBase.executeUpdate("CREATE TABLE tb_dados_processados (\n" +
                                "id TEXT(36) PRIMARY KEY UNIQUE,\n" +
                                "data_estudo TEXT(16),\n" +
                                "periodo_estudo INTEGER,\n" +
                                "valor REAL,\n" +
                                "sensor TEXT(100),\n" +
                                "id_estacao INTEGER,\n" +
                                "id_estudo INTEGER,\n" +
                                "CONSTRAINT id_estacao_fk FOREIGN KEY (id_estacao) REFERENCES tb_estacao(id),\n" +
                                "CONSTRAINT id_estudo_fk FOREIGN KEY (id_estudo) REFERENCES tb_estudo(id)\n" +
                                ");");
       //Criar tabela de dados medidos
        sttmBase.executeUpdate("CREATE TABLE tb_dados_medidos (\n" +
                                "id TEXT(36) PRIMARY KEY UNIQUE,\n" +
                                "data_medicao TEXT(16),\n" +
                                "periodo_medicao INTEGER,\n" +
                                "valor REAL,\n" +
                                "sensor TEXT(100),\n" +
                                "id_estacao INTEGER,\n" +
                                "CONSTRAINT id_estacao_fk FOREIGN KEY (id_estacao) REFERENCES tb_estacao(id)\n" +
                                ");");
//       
//       sttmBase.executeUpdate("pragma journal_mode = WAL;\n" +
//                              "pragma synchronous = normal;\n" +
//                              "pragma journal_size_limit = 6144000;"+
//"");
//       sttmBase.executeUpdate("PRAGMA journal_mode = OFF;\n" +
//                                "PRAGMA synchronous = 0;\n" +
//                                "PRAGMA cache_size = 1000000;\n" +
//                                "PRAGMA locking_mode = EXCLUSIVE;\n" +
//                                "PRAGMA temp_store = MEMORY;");

       
     
    }
    
    private static void popularTabelas( Statement sttmBase) throws SQLException {
//        popularTabelaSensores(conexao);
    }
    
    public void gravarEstacao(String nomeEstacao, String codigoEstacao, String ufEstacao, String regiaoEstacao, String dataFundacao, String latitude, String Longitude, String altitude) {
        this.conectarBanco();
        this.estacaoDao.gravarEstacao(conexao, nomeEstacao, codigoEstacao, ufEstacao, regiaoEstacao, dataFundacao, latitude, Longitude, altitude);
        this.desconectarBanco();
    }
    
    public void gravarDados (String codigoEstacao, String dataMedicao, String peridoMedicao, String valorMedicao, String nomeSensor){
        String idEstacao;
        this.conectarBanco();
        idEstacao = String.valueOf(this.estacaoDao.getIdEstacao(conexao, codigoEstacao));
        this.dadosDao.gravarDado(conexao, idEstacao, dataMedicao, peridoMedicao, valorMedicao, nomeSensor);
        this.desconectarBanco();
    }
    
    public void gravarDados (List<Dados> lista, String codigoEstacao){
        this.conectarBanco();
        String idEstacao;
        idEstacao = String.valueOf(this.estacaoDao.getIdEstacao(conexao, codigoEstacao));
        this.dadosDao.gravarListDados(conexao, lista, idEstacao);
        this.desconectarBanco();
    }

    
    
    
    
    
    
    private static void popularTabelaSensores(Connection conexao) throws SQLException{
        Statement sttm = conexao.createStatement();
        sttm.executeUpdate(""
                +   "INSERT INTO tb_sensor(txt_arquivo_carga, nome, unidade_medida) VALUES('PRECIPITACAO TOTAL, HORARIO(mm)', 'Precipitação Total', 'mm');\n" +
                    "INSERT INTO tb_sensor(txt_arquivo_carga, nome, unidade_medida) VALUES('PRESSAO ATMOSFERICA AO NIVEL DA ESTACAO, HORARIA(mB)', 'Pressão Atmorférica (Nível Estação)', 'mB');\n" +
                    "INSERT INTO tb_sensor(txt_arquivo_carga, nome, unidade_medida) VALUES('PRESSAO ATMOSFERICA REDUZIDA NIVEL DO MAR, AUT(mB)', 'Pressão Atmosférica (Nível Mar)', 'mB');\n" +
                    "INSERT INTO tb_sensor(txt_arquivo_carga, nome, unidade_medida) VALUES('PRESSAO ATMOSFERICA MAX.NA HORA ANT. (AUT)(mB)', 'Pressão Atmosférica Máxima (Última Hora)', 'mB');\n" +
                    "INSERT INTO tb_sensor(txt_arquivo_carga, nome, unidade_medida) VALUES('PRESSAO ATMOSFERICA MIN. NA HORA ANT. (AUT)(mB)', 'Pressão Atmosférica Mínima (Ultima Hora)', 'mb');\n" +
                    "INSERT INTO tb_sensor(txt_arquivo_carga, nome, unidade_medida) VALUES('RADIACAO GLOBAL(Kj/m²)', 'Radiação Global', 'Kj/m²');\n" +
                    "INSERT INTO tb_sensor(txt_arquivo_carga, nome, unidade_medida) VALUES('TEMPERATURA DO AR - BULBO SECO, HORARIA(°C)', 'Temperatura do Ar (Bulbo Seco)', '°C');\n" +
                    "INSERT INTO tb_sensor(txt_arquivo_carga, nome, unidade_medida) VALUES('TEMPERATURA DO PONTO DE ORVALHO(°C)', 'Temperatura do Ponto de Orvalho', '°C');\n" +
                    "INSERT INTO tb_sensor(txt_arquivo_carga, nome, unidade_medida) VALUES('TEMPERATURA MAXIMA NA HORA ANT. (AUT)(°C)', 'Temperatura Máxima (Última Hora)', '°C');\n" +
                    "INSERT INTO tb_sensor(txt_arquivo_carga, nome, unidade_medida) VALUES('TEMPERATURA MINIMA NA HORA ANT. (AUT)(°C)', 'Temperatura Mínima (Última Hora)', '°C');\n" +
                    "INSERT INTO tb_sensor(txt_arquivo_carga, nome, unidade_medida) VALUES('TEMPERATURA ORVALHO MAX. NA HORA ANT. (AUT)(°C)', 'Temperatura Ponto de Orvalho Máxima (Última Hora)', '°C');\n" +
                    "INSERT INTO tb_sensor(txt_arquivo_carga, nome, unidade_medida) VALUES('TEMPERATURA ORVALHO MIN. NA HORA ANT. (AUT)(°C)', 'Temperatura Ponto de Orvalho Mínima (Última Hora)', '°C');\n" +
                    "INSERT INTO tb_sensor(txt_arquivo_carga, nome, unidade_medida) VALUES('UMIDADE REL. MAX. NA HORA ANT. (AUT)(%)', 'Umidade Relativa do Ar Máxima (Última Hora)', '%');\n" +
                    "INSERT INTO tb_sensor(txt_arquivo_carga, nome, unidade_medida) VALUES('UMIDADE REL. MIN. NA HORA ANT. (AUT)(%)', 'Umidade Relativa do Ar Mínima (Última Hora)', '%');\n" +
                    "INSERT INTO tb_sensor(txt_arquivo_carga, nome, unidade_medida) VALUES('UMIDADE RELATIVA DO AR, HORARIA(%)', 'Umidade Relativa do Ar', '%');\n" +
                    "INSERT INTO tb_sensor(txt_arquivo_carga, nome, unidade_medida) VALUES('VENTO, DIRECAO HORARIA (gr(° (gr))', 'Vento (Direção Horária)', 'gr(° (gr)');\n" +
                    "INSERT INTO tb_sensor(txt_arquivo_carga, nome, unidade_medida) VALUES('VENTO, RAJADA MAXIMA(m/s)', 'Vento (Rajada Máxima)', 'm/s');\n" +
                    "INSERT INTO tb_sensor(txt_arquivo_carga, nome, unidade_medida) VALUES('VENTO, VELOCIDADE HORARIA(m/s)', 'Vento (Velocidade Horária)', 'm/s');"
                + "");
        
    }
    
}
