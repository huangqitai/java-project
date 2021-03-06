package shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Set;

public class DatabaseRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

		//能进入到这里，表示账号已经通过验证了
		String userName =(String) principalCollection.getPrimaryPrincipal();
		//通过DAO获取角色和权限
		Set<String> permissions = new DAO().listPermissions(userName);
		Set<String> roles = new DAO().listRoles(userName);

		//授权对象
		SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
		//把通过DAO获取到的角色和权限放进去
		s.setStringPermissions(permissions);
		s.setRoles(roles);
		return s;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取账号密码
		UsernamePasswordToken t = (UsernamePasswordToken) token;
		String userName= token.getPrincipal().toString();
		String password =new String(t.getPassword());
		//获取数据库中的密码

		User user = new DAO().getUser(userName);
		String passwordInDB = user.getPassword();
		String salt = user.getSalt();
		String passwordEncoded = new SimpleHash("md5",password,salt,2).toString();

		if(null==user || !passwordEncoded.equals(passwordInDB))
			throw new AuthenticationException();

		//认证信息里存放账号密码, getName() 是当前Realm的继承方法,通常返回当前类名 :databaseRealm
		SimpleAuthenticationInfo a = new SimpleAuthenticationInfo(userName,password,getName());
		return a;
	}

}

