package usp.mac321.ep2.ex02;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import usp.mac321.ep2.ex01.*;

public class UsuarioManager implements WriterDAO<Usuario>, GetterDAO<Usuario>, ModifyDAO<Usuario> {

    private List<Usuario> usuarios;

    public UsuarioManager(List<Usuario> usuarios) {
        this.usuarios = new ArrayList<Usuario>();
        for (Usuario usuario : usuarios) {
            this.usuarios.add(usuario);
        }
    }

    @Override
    public boolean add(Usuario object) {
        if (object == null)
            return false;
        if (usuarioExiste(object)) {
            return false;
        }
        usuarios.add(object);
        return true;
    }

    @Override
    public boolean remove(Usuario object) {
        if (object == null)
            return false;
        for (Usuario usuario : usuarios) {
            if (usuario.equals(object)) {
                usuarios.remove(usuario);
                return true;
            }
        }
        return false;
    }

    @Override
    public Usuario get(Usuario user) {
        if (user == null)
            return null;
        for (Usuario u : usuarios) {
            if (u.equals(user))
                return u;
        }
        return null;
    }

    @Override
    public List<Usuario> getAll() {
        return usuarios;
    }

    @Override
    public void write(String address) {
        try {
            PrintWriter writer = new PrintWriter(address, "UTF-8");
            writer.println("Apelido,Nome");
            for (Usuario student : this.usuarios) {
                writer.println(student.getApelido() + "," + student.getNome());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Encoding não suportado");
        }
    }

    private boolean usuarioExiste(Usuario user) {
        for (Usuario u : usuarios) {
            if (u.equals(user))
                return true;
        }
        return false;
    }

}
