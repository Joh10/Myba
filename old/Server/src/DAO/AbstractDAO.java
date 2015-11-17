package DAO;

import DAO.utils.Pair;
import DAO.utils.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T>
{
    private final static String adresseIP = "127.0.0.1"; // 127.0.0.1 ou 172.16.110.172
    private final static String port = "1521"; // 11521 si tunel, 1521 sinon
    private final static String accessName = "O11etu2";
    private final static String userName = "Myba";
    private final static String password = "mybaforthewin666";
    private static Connection connection;

    //ORACLE INTERNAL OR EXTERNAL CONNECTION
    //Class.forName("oracle.jdbc.driver.OracleDriver");
    //connection = DriverManager.getConnection("jdbc:oracle:thin:@" + adresseIP + ":" + port + ":" + accessName, userName, password);

    //H2 CONNECTION YOU HAVE TO CHANGE THE PATH TO THE FILE !
    static
    {
        try
        {
            Class.forName("org.h2.Driver");
            //            connection = DriverManager.getConnection("jdbc:h2:file:/Users/Pyroh/Documents/ecole/projet-integre/embeddedDB");
            //connection = DriverManager.getConnection("jdbc:h2:file:D:/projet-integre/embeddedDB"); //FIXME CHANGE MY PATH !
            // connection = DriverManager.getConnection("jdbc:h2:file:C:\\Users\\Benjamin\\Documents\\GitHub/projet-integre/embeddedDB"); //FIXME CHANGE MY PATH !
                connection = DriverManager.getConnection("jdbc:h2:file:/Users/Jo/projet-integre/embeddedDB"); //FIXME CHANGE MY PATH !
            connection.setAutoCommit(false);
            System.err.println("Connected to the embedded database!");
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }

    //Get a prepare statement, same as the normal method but without exception
    private PreparedStatement prepare(String sql)
    {
        try
        {
            return connection.prepareStatement(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    //Generic method to execute a statement, to avoid try catch everywhere in the code
    private void execute(PreparedStatement statement)
    {
        try
        {
            //Execution
            statement.executeUpdate();
            connection.commit();
        }
        catch (SQLException e)
        {
            e.printStackTrace();

            try
            {
                //Une erreur SQL s'est produite on rollback
                if (connection != null) connection.rollback();
            }
            catch (SQLException e1)
            {
                e1.printStackTrace();
            }
        }
        finally
        {
            try
            {
                if (statement != null) statement.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    private List<T> findCollection(PreparedStatement statement)
    {
        List<T> result = new ArrayList<>();

        try
        {
            ResultSet res = statement.executeQuery();

            while (res.next()) result.add(getObject(res));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (statement != null) statement.close();
            }
            catch (SQLException sqlEx)
            {
                sqlEx.printStackTrace();
            }
        }

        return result;
    }

    public List<T> findAll()
    {
        return findCollection(prepare("SELECT * FROM " + getTableName()));
    }

    private void addObjectToStatement(PreparedStatement statement, int index, Object o)
    {
        try
        {
            if (o == null)
            {
                statement.setNull(index, Types.INTEGER);
                return;
            }

            switch (o.getClass().getName())
            {
                case "java.lang.String":
                    statement.setString(index, (String) o);
                    break;
                case "java.lang.Double":
                    statement.setDouble(index, (Double) o);
                    break;
                case "java.util.Date":
                    statement.setDate(index, (java.sql.Date) o);
                    break;
                case "java.lang.Integer":
                    statement.setInt(index, (Integer) o);
                    break;
                case "java.lang.Boolean":
                    statement.setBoolean(index, (Boolean) o);
                    break;
            }
        }
        catch (SQLException sqlExp)
        {
            sqlExp.printStackTrace();
        }
    }

    public void insert(T object)
    {
        //Construction de la query
        StringBuilder builder = new StringBuilder("INSERT INTO " + getTableName() + " (");
        StringBuilder valuesBuilder = new StringBuilder("( ?");

        //Construit la requete
        Query data = getInsertQuery(object);

        //Set le nom des colonnes
        for (int i = 0; i < data.size(); i++)
        {
            if (i == data.size() - 1) builder.append(data.getDataPairs().get(i).getName());
            else builder.append(data.getDataPairs().get(i).getName()).append(", ");
        }

        for (int j = 1; j < data.size(); j++)
            valuesBuilder.append(", ?");

        valuesBuilder.append(" );");
        builder.append(" ) VALUES ");
        builder.append(valuesBuilder);

        PreparedStatement statement = prepare(builder.toString());
        int i = 1;

        //Set les values
        for (Pair pair : data.getDataPairs())
        {
            addObjectToStatement(statement, i, pair.getData());
            i++;
        }

        //Execution
        execute(statement);
    }

    public void update(T object)
    {
        //Construction de la query
        StringBuilder builder = new StringBuilder("UPDATE " + getTableName() + " SET ");
        Query data = getUpdateQuery(object);

        //Set le nom des colonnes
        for (int i = 0; i < data.size(); i++)
        {
            if (i == data.size() - 1) builder.append(data.getDataPairs().get(i).getName()).append("= ?");
            else builder.append(data.getDataPairs().get(i).getName()).append("= ?, ");
        }

        builder.append(" WHERE ").append(data.getWhereClause().getName()).append(" = ?");

        //Set les valeurs
        PreparedStatement statement = prepare(builder.toString());
        int i = 1;

        //Set les valeurs
        for (Pair pair : data.getDataPairs())
        {
            addObjectToStatement(statement, i, pair.getData());
            i++;
        }

        //Set la clause where
        addObjectToStatement(statement, i, data.getWhereClause().getData());

        //Execution
        execute(statement);
    }

    public void delete(T object)
    {
        //Construction de la query
        StringBuilder builder = new StringBuilder("DELETE FROM " + getTableName() + " WHERE ");
        Query data = getDeleteQuery(object);

        //Set le nom des colonnes
        for (int i = 0; i < data.size(); i++)
        {
            if (i == data.size() - 1) builder.append(data.getDataPairs().get(i).getName()).append("= ?");
            else builder.append(data.getDataPairs().get(i).getName()).append("= ? AND ");
        }

        //Remplissage de la query
        PreparedStatement statement = prepare(builder.toString());

        //Set les valeurs
        int i = 1;
        for (Pair pair : data.getDataPairs())
        {
            addObjectToStatement(statement, i, pair.getData());
            i++;
        }

        //Execution
        execute(statement);
    }

    protected final List<T> findFromLabel(Object value, String label) throws IndexOutOfBoundsException
    {
        return findFromLabels(new Object[]{value}, label);
    }

    final List<T> findFromLabels(Object[] values, String... labels) throws IndexOutOfBoundsException
    {
        if (labels.length == 0 || values.length == 0 || (labels.length != values.length))
            throw new IndexOutOfBoundsException();


        StringBuilder builder = new StringBuilder("SELECT * FROM " + getTableName() + " WHERE ");

        //Set le nom des colonnes
        for (int i = 0; i < labels.length; i++)
        {
            if (i == labels.length - 1) builder.append(labels[i]).append("= ?");
            else builder.append(labels[i]).append("= ? OR ");
        }

        PreparedStatement statement = prepare(builder.toString());

        for (int j = 0; j < values.length; ++j)
            addObjectToStatement(statement, j + 1, values[j]);

        return findCollection(statement);
    }

    protected abstract String getTableName();

    public abstract T findById(Object... id);

    protected abstract Query getInsertQuery(T object);

    protected abstract Query getUpdateQuery(T object);

    protected abstract Query getDeleteQuery(T object);

    protected abstract T getObject(ResultSet res) throws SQLException;
}
