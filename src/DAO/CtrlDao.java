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
import java.util.ArrayList;
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
    private SensorDAO sensorDAO;
    
    public CtrlDao(String caminhoBanco) {
        this.caminhoBanco = caminhoBanco;
        this.estacaoDao = new EstacaoDAO(this);
        this.dadosDao = new DadosDAO(this);
        this.colunaDAO = new ColunaDAO(this);
        this.sensorDAO = new SensorDAO(this);
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
                System.out.println("Uma nova base foi criada..");
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
       
        System.out.println("Criando tabela de Sensores");
        sttmBase.executeUpdate("CREATE TABLE tb_sensor(\n" +
                                "id TEXT(36) PRIMARY KEY,\n" +
                                "nome TEXT(50),\n" +
                                "txt_arquivo_carga TEXT(255),\n" +
                                "unidade_medida TEXT(512));");
        
        System.out.println("Criando tabela de Metodologias");
        sttmBase.executeUpdate("CREATE TABLE tb_metodologia (\n" +
                                "id TEXT(36) PRIMARY KEY UNIQUE,\n" +
                                "nome TEXT(50),\n" +
                                "dsc TEXT(255),\n" +
                                "dsc_ajuda TEXT(512));");

        System.out.println("Criando tabela de Estacoes");
         sttmBase.executeUpdate("CREATE TABLE tb_estacao (\n" +
                                "nome TEXT(50),\n" +
                                "codigo TEXT(10),\n" +
                                "latitude REAL,\n" +
                                "longitude REAL,\n" +
                                "altitude REAL,\n" +
                                "periodicidade TEXT(24),\n" +
                                "data_cadastro TEXT(32),\n" +
                                "data_fundacao TEXT(32),\n" +
//                                "uf TEXT(2),\n" +
//                                "regiao TEXT(2),\n" +
                                "id TEXT(36) PRIMARY KEY UNIQUE" +
                                ");");
        System.out.println("Criando tabela de Estudos");
        sttmBase.executeUpdate("CREATE TABLE tb_estudo (\n" +
                                "id TEXT(36) PRIMARY KEY UNIQUE,\n" +
                                "id_estacao INTEGER,\n" +
                                "id_metodologia INTEGER,\n" +
                                "data_estudo TEXT(32),\n" +
                                "FOREIGN KEY (id_metodologia) REFERENCES tb_metodologia(id),\n" +
                                "FOREIGN KEY (id_estacao) REFERENCES tb_estacao(id)\n" +
                                ");");
        System.out.println("Criando tabela de Dados Processados");
        sttmBase.executeUpdate("CREATE TABLE tb_dados_processados (\n" +
                                "id TEXT(36) PRIMARY KEY UNIQUE,\n" +
                                "data_estudo TEXT(32),\n" +
                                "periodo_estudo INTEGER,\n" +
                                "valor REAL,\n" +
                                "id_estacao INTEGER,\n" +
                                "id_estudo INTEGER,\n" +
                                "id_sensor INTEGER,\n" +
                                "FOREIGN KEY (id_estacao) REFERENCES tb_estacao(id),\n" +
                                "FOREIGN KEY (id_estudo) REFERENCES tb_estudo(id),\n" +
                                "FOREIGN KEY (id_sensor) REFERENCES tb_sensor(id)\n" +
                                ");");
        System.out.println("Criando tabela de Dados Medidos");
        sttmBase.executeUpdate("CREATE TABLE tb_dados_medidos (\n" +
                                "id TEXT(36) PRIMARY KEY UNIQUE,\n" +
                                "data_medicao TEXT(32),\n" +
                                "periodo_medicao INTEGER,\n" +
                                "valor REAL,\n" +
                                "id_estacao INTEGER,\n" +
                                "id_sensor INTEGER,\n" +
                                "FOREIGN KEY (id_estacao) REFERENCES tb_estacao(id),\n"+
                                "FOREIGN KEY (id_sensor) REFERENCES tb_sensor(id)\n" +
                                ");");
        System.out.println("Criando tabela de Parametros");
        sttmBase.executeUpdate("CREATE TABLE tb_parametros (\n" +
                                "id	TEXT(36) NOT NULL,\n" +
                                "valor	REAL,\n" +
                                "id_estudo	TEXT NOT NULL,\n" +
                                "num_ordem INTEGER,\n" +
                                "FOREIGN KEY(id_estudo) REFERENCES tb_estudo,\n" +
                                "PRIMARY KEY(id)\n" +
                                ");");
        
        System.out.println("Criando tabela de Relatorio Mensal");
        sttmBase.executeUpdate("CREATE TABLE tb_relatorio_mensal (\n" +
                                "id	TEXT NOT NULL,\n" +
                                "id_estudo	TEXT(36) NOT NULL,\n" +
                                "id_sensor	TEXT(36) NOT NULL,\n" +
                                "mes	TEXT(32),\n" +
                                "desv_abs_medio	REAL,\n" +
                                "erro_abs_medio	REAL,\n" +
                                "erro_abs_medio_per	REAL,\n" +
                                "FOREIGN KEY(id_sensor) REFERENCES tb_sensor,\n" +
                                "FOREIGN KEY(id_estudo) REFERENCES tb_estudo,\n" +
                                "PRIMARY KEY(id)\n" +
                                ");");
        System.out.println("Criando tabela de Relatorio Erro");
        sttmBase.executeUpdate("CREATE TABLE tb_relatorio_erro (\n" +
                                "id	TEXT(36) NOT NULL,\n" +
                                "id_estudo	TEXT(36) NOT NULL,\n" +
                                "mes	TEXT(32),\n" +
                                "qtd_erros_mes	INTEGER,\n" +
                                "qtd_nulls_mes	INTEGER,\n" +
                                "qtd_subs_mes	INTEGER,\n" +
                                "qtd_elem_mes	INTEGER,\n" + 
                                "FOREIGN KEY(id_estudo) REFERENCES tb_estudo,\n" +
                                "PRIMARY KEY(id)\n" +
                                ");");
        System.out.println("Criando tabela de Relatorio Coeficientes");
        sttmBase.executeUpdate("CREATE TABLE tb_relatorio_coeficientes (\n" +
                                "id	TEXT(36) NOT NULL,\n" +
                                "id_estudo	TEXT(36) NOT NULL,\n" +
                                "tendencia	REAL,\n" +
                                "coef_sperman	REAL,\n" +
                                "mes	TEXT(32),\n" +
                                "FOREIGN KEY(id_estudo) REFERENCES tb_estudo,\n" +
                                "PRIMARY KEY(id)\n" +
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
        popularTabelaSensores(sttmBase);
    }
    
    public void gravarEstacao(String idEstacao, String nomeEstacao, String codigoEstacao, String dataFundacao, String latitude, String Longitude, String altitude, String periodicidade) {
        this.conectarBanco();
        if (dataFundacao == null){
            this.estacaoDao.gravarEstacaoConvencional(conexao,idEstacao, nomeEstacao, codigoEstacao, latitude, Longitude, altitude, periodicidade);
        } else {
            this.estacaoDao.gravarEstacaoAutomatica(conexao, idEstacao, nomeEstacao, codigoEstacao, dataFundacao, latitude, Longitude, altitude, periodicidade);
        }
        this.desconectarBanco();
    }
    
    public void gravarDados (String codigoEstacao, String dataMedicao, String peridoMedicao, String valorMedicao, String nomeSensor, String idDado){
        String idEstacao;
        this.conectarBanco();
        idEstacao = String.valueOf(this.estacaoDao.getIdEstacao(conexao, codigoEstacao));
        this.dadosDao.gravarDado(conexao, idDado, idEstacao, dataMedicao, peridoMedicao, valorMedicao, nomeSensor);
        this.desconectarBanco();
    }
    
    public void gravarDados (List<Dados> lista, String codigoEstacao){
        this.conectarBanco();
        String idEstacao;
        idEstacao = String.valueOf(this.estacaoDao.getIdEstacao(conexao, codigoEstacao));
        this.dadosDao.gravarListDados(conexao, lista, idEstacao);
        this.desconectarBanco();
    }
    
    public void gravarListaColunas (List<Coluna> lista, String codigoEstacao){
        this.conectarBanco();
        this.colunaDAO.gravarListaColunas(conexao, lista, codigoEstacao);
        this.desconectarBanco();
    }

    public List<Sensor> listarSensores(){
        ArrayList<Sensor> lista;
                
        this.conectarBanco();
        
        lista = (ArrayList<Sensor>) this.sensorDAO.listarSensores(conexao);
        
        this.desconectarBanco();
        
        return lista;
    }
    
    public List<Estacao> listarEstacoes(){
        ArrayList<Estacao> lista =null;
        this.conectarBanco();
        lista = (ArrayList<Estacao>) this.estacaoDao.listarEstacoes(conexao);
        this.desconectarBanco();
        return lista;
    }
    
    public List<String>listarAnosDadosMedidosEstacoes( String codigoEstacao){
        ArrayList<String> lista = null;
        String idEstacao;
        this.conectarBanco();
        idEstacao = this.estacaoDao.getIdEstacao(conexao, codigoEstacao);
        lista = (ArrayList<String>) this.dadosDao.listarAnosDadosMedidosEstacoes(conexao, idEstacao);
        this.desconectarBanco();
        return lista;
    }
    
    public List<Dados> listarDadosEstacaoMes (String codigoEstacao, String ano, Sensor sensor){
        ArrayList<Dados> lista;
        this.conectarBanco();
        lista = (ArrayList<Dados>) dadosDao.listarDadosEstacaoMes(conexao, codigoEstacao, ano, sensor);
        this.desconectarBanco();
        return lista;
        
    }
    
    public Estacao getEstacao (String codigo) {
        Estacao nova;
        this.conectarBanco();
        nova = this.estacaoDao.getEstacao(conexao, codigo);
        this.desconectarBanco();
        return nova;
    }
    
    public Info getMedicaoPeriodo(String codigo, String periodo){
        Info medicao = null;
        Estacao nova = this.getEstacao(codigo);
        
        if (nova == null){
            return null;
        }
        
        medicao = new Info();
        
        medicao.setEstacao(nova);
        
        medicao.setLista(this.colunaDAO.getColunasEstacaoPeriodo(codigo, periodo, medicao));
        
        medicao.setListaAnos(this.listarAnosDadosMedidosEstacoes(codigo));
        
        return medicao;        
    }
       
    
    
    private static void popularTabelaSensores(Statement sttmBase) throws SQLException{
        
        sttmBase.executeUpdate(""
                +   "INSERT INTO tb_sensor(id, txt_arquivo_carga, nome, unidade_medida) VALUES('3043bee9-3ad6-44fe-b1a1-2f2018c58df2','PRECIPITACAO TOTAL, HORARIO(mm)', 'Precipitação Total', 'mm');\n" +
                    "INSERT INTO tb_sensor(id, txt_arquivo_carga, nome, unidade_medida) VALUES('a4573ace-31eb-4db5-97cf-5a9c21f19679','PRESSAO ATMOSFERICA AO NIVEL DA ESTACAO, HORARIA(mB)', 'Pressão Atmosférica (Nível Estação)', 'mB');\n" +
                    "INSERT INTO tb_sensor(id, txt_arquivo_carga, nome, unidade_medida) VALUES('64cea8eb-cbb8-4573-be49-2fd3bdd1b2a1','PRESSAO ATMOSFERICA REDUZIDA NIVEL DO MAR, AUT(mB)', 'Pressão Atmosférica (Nível Mar)', 'mB');\n" +
                    "INSERT INTO tb_sensor(id, txt_arquivo_carga, nome, unidade_medida) VALUES('0295b45b-dfb1-4336-b9c1-01e95caf5b43','PRESSAO ATMOSFERICA MAX.NA HORA ANT. (AUT)(mB)', 'Pressão Atmosférica Máxima (Última Hora)', 'mB');\n" +
                    "INSERT INTO tb_sensor(id, txt_arquivo_carga, nome, unidade_medida) VALUES('18d6c881-5609-4453-8432-355a094a691f','PRESSAO ATMOSFERICA MIN. NA HORA ANT. (AUT)(mB)', 'Pressão Atmosférica Mínima (Ultima Hora)', 'mb');\n" +
                    "INSERT INTO tb_sensor(id, txt_arquivo_carga, nome, unidade_medida) VALUES('2f579447-bf17-4987-9e8c-e6c1fe0fb67d','RADIACAO GLOBAL(Kj/m²)', 'Radiação Global', 'Kj/m²');\n" +
                    "INSERT INTO tb_sensor(id, txt_arquivo_carga, nome, unidade_medida) VALUES('98e84541-dd95-4290-a5a2-da60784effcb','TEMPERATURA DO AR - BULBO SECO, HORARIA(°C)', 'Temperatura do Ar (Bulbo Seco)', '°C');\n" +
                    "INSERT INTO tb_sensor(id, txt_arquivo_carga, nome, unidade_medida) VALUES('f2578f2f-7201-4c3f-92d7-174f23fe0989','TEMPERATURA DO PONTO DE ORVALHO(°C)', 'Temperatura do Ponto de Orvalho', '°C');\n" +
                    "INSERT INTO tb_sensor(id, txt_arquivo_carga, nome, unidade_medida) VALUES('5c56cdf0-62aa-4f15-8379-e497e06ff481','TEMPERATURA MAXIMA NA HORA ANT. (AUT)(°C)', 'Temperatura Máxima (Última Hora)', '°C');\n" +
                    "INSERT INTO tb_sensor(id, txt_arquivo_carga, nome, unidade_medida) VALUES('59ee016a-027d-40ea-92e6-cd8a76f7fa4e','TEMPERATURA MINIMA NA HORA ANT. (AUT)(°C)', 'Temperatura Mínima (Última Hora)', '°C');\n" +
                    "INSERT INTO tb_sensor(id, txt_arquivo_carga, nome, unidade_medida) VALUES('e5cf1df4-3c06-4385-a158-b085c5fe501a','TEMPERATURA ORVALHO MAX. NA HORA ANT. (AUT)(°C)', 'Temperatura Ponto de Orvalho Máxima (Última Hora)', '°C');\n" +
                    "INSERT INTO tb_sensor(id, txt_arquivo_carga, nome, unidade_medida) VALUES('701795db-119d-4975-9667-30c159421183','TEMPERATURA ORVALHO MIN. NA HORA ANT. (AUT)(°C)', 'Temperatura Ponto de Orvalho Mínima (Última Hora)', '°C');\n" +
                    "INSERT INTO tb_sensor(id, txt_arquivo_carga, nome, unidade_medida) VALUES('215a59b9-1aed-41e8-af90-f06808238cb1','UMIDADE REL. MAX. NA HORA ANT. (AUT)(%)', 'Umidade Relativa do Ar Máxima (Última Hora)', '%');\n" +
                    "INSERT INTO tb_sensor(id, txt_arquivo_carga, nome, unidade_medida) VALUES('d4017b10-b51c-4ddc-8ab8-a787bedd17c4','UMIDADE REL. MIN. NA HORA ANT. (AUT)(%)', 'Umidade Relativa do Ar Mínima (Última Hora)', '%');\n" +
                    "INSERT INTO tb_sensor(id, txt_arquivo_carga, nome, unidade_medida) VALUES('78b5bcc2-35ba-4c1c-9120-90c46330f64e','UMIDADE RELATIVA DO AR, HORARIA(%)', 'Umidade Relativa do Ar', '%');\n" +
                    "INSERT INTO tb_sensor(id, txt_arquivo_carga, nome, unidade_medida) VALUES('7e1890c6-07ce-4bd4-bf9a-bdcb61994bc4','VENTO, DIRECAO HORARIA (gr)(° (gr))', 'Vento (Direção Horária)', 'gr(° (gr)');\n" +
                    "INSERT INTO tb_sensor(id, txt_arquivo_carga, nome, unidade_medida) VALUES('bcda33c6-8600-42e2-99df-95869d3cf26c','VENTO, RAJADA MAXIMA(m/s)', 'Vento (Rajada Máxima)', 'm/s');\n" +
                    "INSERT INTO tb_sensor(id, txt_arquivo_carga, nome, unidade_medida) VALUES('0802ebd3-af13-4c16-ba96-b416d02e05a4','VENTO, VELOCIDADE HORARIA(m/s)', 'Vento (Velocidade Horária)', 'm/s');"
                + "");
        
    }
    
}
