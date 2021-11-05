package by.bsuir.command.userCommand;

import by.bsuir.command.Command;
import by.bsuir.command.Constant;
import by.bsuir.command.RequestCommand;
import by.bsuir.command.ResponseCommand;

import javax.servlet.http.Part;

public class CreateProductCommand implements Command {
    @Override
    public ResponseCommand execute(RequestCommand requestCommand) {
        ResponseCommand response = new ResponseCommand();
        String name = requestCommand.getRequestParameter(Constant.PARAMETER_PRODUCT_NAME);
        String date = requestCommand.getRequestParameter(Constant.PARAMETER_PRODUCT_DATE);
        String run = requestCommand.getRequestParameter(Constant.PARAMETER_PRODUCT_RUN);
        String volume_engine = requestCommand.getRequestParameter(Constant.PARAMETER_PRODUCT_ENGINE);
        String price = requestCommand.getRequestParameter(Constant.PARAMETER_PRODUCT_PRICE);
        String type_transmission = requestCommand.getRequestParameter(Constant.PARAMETER_TYPE_TRANSMISSION);
        String description = requestCommand.getRequestParameter(Constant.PARAMETER_PRODUCT_DESCRIPTION);

        Part part_1 = requestCommand.getRequestPart(Constant.PARAMETER_PRODUCT_IMAGE_1);
        Part part_2 = requestCommand.getRequestPart(Constant.PARAMETER_PRODUCT_IMAGE_2);
        Part part_3 = requestCommand.getRequestPart(Constant.PARAMETER_PRODUCT_IMAGE_3);
        Part part_4 = requestCommand.getRequestPart(Constant.PARAMETER_PRODUCT_IMAGE_4);
        Part part_5 = requestCommand.getRequestPart(Constant.PARAMETER_PRODUCT_IMAGE_5);
        Part part_6 = requestCommand.getRequestPart(Constant.PARAMETER_PRODUCT_IMAGE_6);


        System.out.println(name);
        System.out.println(date);
        System.out.println(run);
        System.out.println(volume_engine);
        System.out.println(price);
        System.out.println(type_transmission);
        System.out.println(description);

        System.out.println(part_1.getContentType());
        System.out.println(part_2.getContentType());
        System.out.println(part_3.getContentType());
        System.out.println(part_4.getContentType());
        System.out.println(part_5.getContentType());
        System.out.println(part_6.getContentType());

        return null;
    }
}
