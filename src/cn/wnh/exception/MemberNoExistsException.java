package cn.wnh.exception;

@SuppressWarnings("serial")
public class MemberNoExistsException extends MemberLoginException {

	public MemberNoExistsException(String msg) {
		super(msg);
	}

}
