import java.lang.reflect.Method;

class Demo {

    public static boolean isSetter(Method method) {
        if (!method.getName().startsWith("set")) return false;
        if (method.getParameterTypes().length != 1) return false;
        return true;
    }

    public static void main(String args[]) {

        //change this to lines to your view and dto objects
        CatDto dto = new CatDto();
        CatView view = new CatView();


        String dtoClassName = dto.getClass().getName();
        String viewClassName = view.getClass().getName();

        Method[] dtoMethods = dto.getClass().getMethods();
        Method[] viewMethods = dto.getClass().getMethods();


        System.out.printf("import java.util.ArrayList;\nimport java.util.List;\n\n");
        System.out.printf("public class %sMapper {\n\n", dtoClassName.substring(0, dtoClassName.length() - 3));
        System.out.printf("public %s fromViewToDto(%s view) {\n", dtoClassName, viewClassName);
        System.out.printf("     %s dto = new %s();\n", dtoClassName, dtoClassName);

        for (Method temp : dtoMethods) {
            if (isSetter(temp))
                System.out.printf("     dto.%s(view.get%s());\n", temp.getName(), temp.getName().substring(3));
        }
        System.out.printf("     return dto;\n}\n\n");



        System.out.printf("public %s fromDtoToView(%s dto) {\n", viewClassName, dtoClassName);
        System.out.printf("     %s view = new %s();\n", viewClassName, viewClassName);

        for (int i = 0; i < viewMethods.length; i++) {
            Method temp = viewMethods[i];
            if (isSetter(temp))
                System.out.printf("     view.%s(dto.get%s());\n", temp.getName(), temp.getName().substring(3));
        }
        System.out.printf("     return view;\n}\n\n");



        //for the List of mapped objects
        System.out.printf("public List<%s> fromViewListToDtoList(List<%s> viewList) {\n", dtoClassName, viewClassName);
        System.out.printf("     List<%s> dtoList = new ArrayList<>();\n", dtoClassName);
        System.out.printf("     viewList.forEach(p -> dtoList.add(fromViewToDto(p)));\n");
        System.out.printf("     return dtoList;\n}\n\n");

        System.out.printf("public List<%s> fromDtoListToViewList(List<%s> dtoList) {\n", viewClassName, dtoClassName);
        System.out.printf("     List<%s> viewList = new ArrayList<>();\n", viewClassName);
        System.out.printf("     dtoList.forEach(p -> viewList.add(fromDtoToView(p)));\n");
        System.out.printf("     return viewList;\n}\n\n");

        System.out.print("}");
    }
}






























