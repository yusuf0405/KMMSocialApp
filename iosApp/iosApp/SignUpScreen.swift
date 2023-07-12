import SwiftUI
import shared

struct SignUpScreen: View {
    @State private var nameComponents = PersonNameComponents()
    @State private var username: String = ""

	var body: some View {
        if #available(iOS 15.0, *) {
            TextField("Username", text: $username)
                .disableAutocorrection(true)
                .border(.pink)
                .frame(maxWidth: .infinity,maxHeight: 160)
                .padding(EdgeInsets(top: 20, leading: 10, bottom: 20, trailing: 10)
                )
            
    
        } else {
            // Fallback on earlier versions
        }
       
	}
}

struct SignUpScreen_Previews: PreviewProvider {
	static var previews: some View {
		SignUpScreen()
	}
}
