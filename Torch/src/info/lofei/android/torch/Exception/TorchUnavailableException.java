package info.lofei.android.torch.Exception;

/**
 * @author lofei@duotin.com
 * @version 1.0.0
 *          lofei 2014-10-24 上午11:50
 * @package info.lofei.android.torch.Exception
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD
 */
public class TorchUnavailableException extends RuntimeException {

    public TorchUnavailableException() {
        super();
    }

    public TorchUnavailableException(String detailMessage) {
        super(detailMessage);
    }

    public TorchUnavailableException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public TorchUnavailableException(Throwable throwable) {
        super(throwable);
    }
}
