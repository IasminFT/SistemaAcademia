package seguranca;

/**
 * Enumeração que define os tipos de permissões de acesso para usuários na academia.
 * Utilizada para diferenciar os níveis de acesso entre administradores e funcionários.
 * 
 * @author laviniacharrua e iasmintorres
 */
public enum TipoPermissao {

    /** Permissão de acesso total, geralmente atribuída aos administradores. */
    ACESSO_TOTAL,

    /** Permissão de acesso limitado, geralmente atribuída aos funcionários comuns. */
    ACESSO_LIMITADO
}
