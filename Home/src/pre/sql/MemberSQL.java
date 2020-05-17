package pre.sql;

public class MemberSQL {
	public final int SEL_LOGIN = 1001;
	public final int SEL_IDCK = 1002;
	public final int SEL_ID_INFO = 1003;
	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		switch(code) {
		case SEL_LOGIN:
			buff.append("SELECT ");
			buff.append("  count(*) cnt ");
			buff.append("member ");
			buff.append("WHERE ");
			buff.append("   id = ? ");
			buff.append("   AND pw = ? ");
			break;
		case SEL_IDCK:
			buff.append("SELECT ");
			buff.append("	count(*) cnt ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("id = ? ");
			break;
		case SEL_ID_INFO:
			buff.append("SELECT ");
			buff.append("	cnt, nvl(name, '#') name, nvl(mail, '#'), nvl(tel, '#') tel ");
			buff.append("FROM ");
			buff.append("		( ");
			buff.append("		SELECT ");
			buff.append("			count(*) cnt ");
			buff.append("		FROM ");
			buff.append("			member ");
			buff.append("		WHERE ");
			buff.append("			id = ? ");
			buff.append("id = ? ");
			break;
		}
		return buff.toString();
	}
}
